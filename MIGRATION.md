# Migration Guide

### Release 3.1.0

* New layout shouldn't cause any issues.

* Themes no longer need to be extending a Mal prefixed theme - use MaterialComponents themes as in the following example.

* Layout no longer includes a toolbar - use a theme which includes one:

```xml
<style name="AppTheme.MaterialAboutActivity.Dark" parent="Theme.MaterialComponents">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>
```

* Custom card backgrounds can be defined as so:

```xml
<style name="AppTheme.MaterialAboutActivity.Light.DarkActionBar.CustomCardView" parent="Theme.MaterialComponents.Light.DarkActionBar">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
    <!-- Text colour -->
    <item name="android:textColorPrimary">#ffffff</item>
    <item name="android:textColorSecondary">#ffffff</item>
    <!-- Customise card background and icon colour -->
    <item name="colorSurface">@color/colorPrimaryDark</item>
    <item name="colorOnSurface">#ffffff</item>
</style>
```

* You no longer need to define colours for your icons - let the library handle this, or set a custom colour with `colorOnSurface` attribute in your theme.



### Release 2.3.0

* Changed design of layout, check old layouts