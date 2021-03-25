package com.danielstone.materialaboutlibrary.items;

import android.view.View;

import com.danielstone.materialaboutlibrary.holders.MaterialAboutItemViewHolder;

public abstract class MaterialAboutCheckableItem extends MaterialAboutItem {

	private MaterialAboutOnCheckedChangedAction onCheckedChangedAction;
	private boolean checked;

	public MaterialAboutCheckableItem(CheckableBuilder builder) {
		super();
		this.onCheckedChangedAction = builder.onCheckedChanged;
		this.checked = builder.checked;
	}

	public MaterialAboutCheckableItem(boolean checked, MaterialAboutOnCheckedChangedAction onCheckedChangedAction) {
		super();
		this.onCheckedChangedAction = onCheckedChangedAction;
		this.checked = checked;
	}

	public MaterialAboutCheckableItem(MaterialAboutCheckableItem item) {
		this.onCheckedChangedAction = item.onCheckedChangedAction;
		this.checked = item.checked;
	}

	protected static void setupItem(MaterialAboutCheckableItemViewHolder holder, MaterialAboutCheckableItem item) {
		holder.setMaterialAboutCheckableItem(item);
		holder.setOnCheckedChanged(item.getOnCheckedChangedAction());
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChecked() {
		return checked;
	}

	public MaterialAboutOnCheckedChangedAction getOnCheckedChangedAction() {
		return onCheckedChangedAction;
	}

	public MaterialAboutCheckableItem setOnCheckedChangedAction(MaterialAboutOnCheckedChangedAction onCheckedChanged) {
		this.onCheckedChangedAction = onCheckedChanged;
		return this;
	}

	@Override
	public String getDetailString() {
		return "MaterialAboutCheckableItem{" +
				", onCheckedChangedAction=" + onCheckedChangedAction +
				", checked=" + checked +
				'}';
	}

	public abstract static class MaterialAboutCheckableItemViewHolder extends MaterialAboutItemViewHolder {
		private MaterialAboutOnCheckedChangedAction onCheckedChanged;
		private MaterialAboutCheckableItem materialAboutCheckableItem;
		private boolean broadcasting;

		public MaterialAboutCheckableItemViewHolder(View itemView) {
			super(itemView);
		}

		private void setMaterialAboutCheckableItem(MaterialAboutCheckableItem materialAboutCheckableItem) {
			this.materialAboutCheckableItem = materialAboutCheckableItem;
		}

		private void setOnCheckedChanged(MaterialAboutOnCheckedChangedAction onCheckedChanged) {
			this.onCheckedChanged = onCheckedChanged;
			initActionViewListener(onCheckedChanged != null);
		}

		protected void onCheckedChanged(boolean isChecked) {
			this.materialAboutCheckableItem.setChecked(isChecked);

			// Avoid infinite recursions if this.aSwitch.setChecked() is called from a listener or below
			if (this.broadcasting) {
				return;
			}

			this.broadcasting = true;
			if (this.onCheckedChanged != null) {
				if (!this.onCheckedChanged.onCheckedChanged(this.materialAboutCheckableItem, isChecked)) {
					this.materialAboutCheckableItem.setChecked(!isChecked);
					this.setActionViewChecked(!isChecked);
				}
				else {
					updateSubText(materialAboutCheckableItem);
				}
			}
			this.broadcasting = false;
		}

		protected abstract void initActionViewListener(boolean hasListener);

		protected abstract void setActionViewChecked(boolean isChecked);

		protected abstract void updateSubText(MaterialAboutCheckableItem item);
	}

	public abstract static class CheckableBuilder<T> {

		private MaterialAboutOnCheckedChangedAction onCheckedChanged;
		private boolean checked;

		public T setOnCheckedChanged(MaterialAboutOnCheckedChangedAction onCheckedChanged) {
			this.onCheckedChanged = onCheckedChanged;
			return (T) this;
		}

		public T setChecked(boolean checked) {
			this.checked = checked;
			return (T) this;
		}

		public abstract MaterialAboutCheckableItem build();
	}
}
