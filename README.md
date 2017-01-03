#material-about-library

[![Release][101]][102]
[![Apache License 2.0][103]][104]

**If there are any icon designers out there that are willing to design a free icon for this libraries' demo app, please contact me!**

Makes it easy to create a beautiful about screen for your app.

Idea from here: [Heinrich Reimer's open-source-library-request-manager][6]

Design inspired by Phonograph.

Demo
--------
<a href='https://play.google.com/store/apps/details?id=com.danielstone.materialaboutlibrarydemo&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_gb/badges/images/generic/en_badge_web_generic.png' height="60" /></a>

Screenshots
--------

| Demo App | Example |
|:-:|:-:|:-:|:-:|
| ![Demo App][2] | ![Monitor][4] |
| [_ExampleMaterialAboutActivity.java_][3] | [_Monitor_][5] | 

Features
--------

* Material design
* Modular backend
* Easy to implement
* Simple but intuitive API

Dependency
----------

*material-about-library* is available on [**jitpack.io**][1]

**Gradle dependency:**
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
```gradle
dependencies {
    compile 'com.github.daniel-stoneuk:material-about-library:1.1.1'
}
```

Setup
-----

**Step 1:** Your `Activity` must extend [`MaterialAboutActivity`][7] and be in your *AndroidManifest.xml*:
```java
public class ExampleMaterialAboutActivity extends MaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList() {
        return new MaterialAboutList.Builder()
                .build();
    }
    
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }

}
```
Ensure that the theme extends 'Theme.Mal', and apply primary & accent colours.
```xml
<manifest ...>
    <application ...>
        <activity android:name=".ExampleMaterialAboutActivity"
            android:theme="@style/AppTheme.MaterialAboutActivity"/>
    </application>
</manifest>
```
```xml
    <style name="AppTheme.MaterialAboutActivity" parent="Theme.Mal" >
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
```

**Step 2:** Add Cards:

Start building a "card" using [`MaterialAboutCard.Builder()`][8]
```java
public class ExampleMaterialAboutActivity extends MaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList() {
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();
```
Give the card a title by calling `.title` on the `Builder`
```java
        authorCardBuilder.title("Author");
```

**Step 3:** Add Items:

There are currently two types of item you can add to a card - [`MaterialAboutTitleItem`][9] and [`MaterialAboutActionItem`][10]. Planned items include "person" items which feature buttons to showcase a single person. Feel free to submit a PR or Issue for more item ideas.

- `MaterialAboutActionItem`: Standard item with text, icon and optional subtext.
- `MaterialAboutTitleItem`: Larger item with large icon (eg app icon) and larger text.


[`MaterialAboutTitleItem`][9] is created with [`MaterialAboutTitleItem.Builder()`][9] and lets you specify **text** and an **icon**.
```java
        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("Material About Library")
                .icon(R.mipmap.ic_launcher)
                .build());
```
[`MaterialAboutActionItem`][10] is created with [`MaterialAboutActionItem.Builder()`][10] and lets you specify **text**, **sub-text**, an **icon** and an **OnClickListener**.
```java
        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Version")
                .subText("1.0.0")
                .icon(R.drawable.ic_about_info)
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(ExampleMaterialAboutActivity.this, "Version Tapped", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());
```
**Step 4:** Return the list:
Create a [`MaterialAboutList`][11] using [`MaterialAboutList.Builder()`][11], passing in the cards you would like to display.
```java
        return new MaterialAboutList.Builder()
                .addCard(supportCardBuilder.build())
                .build();
    }
}
```

Check out a working example in [`ExampleMaterialAboutActivity.java`][3].

**Tip:** You can either use *Strings* / *Drawables* or *Resources* when creating `MaterialAboutItem`'s

License
-------

    Copyright 2016 Daniel Stone

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://jitpack.io
[2]: http://i.imgur.com/2d63NFS.png
[3]: https://github.com/daniel-stoneuk/material-about-library/blob/master/app/src/main/java/com/danielstone/materialaboutlibrarydemo/ExampleMaterialAboutActivity.java
[4]: http://i.imgur.com/1k4rcN7.png
[5]: https://play.google.com/store/apps/details?id=com.danielstone.energyhive
[6]: https://github.com/HeinrichReimer/open-source-library-request-manager/issues/3
[7]: https://github.com/daniel-stoneuk/material-about-library/blob/master/library/src/main/java/com/danielstone/materialaboutlibrary/MaterialAboutActivity.java
[8]: https://github.com/daniel-stoneuk/material-about-library/blob/ce35cdc99d33af1faf40c7a1cddf2889898be4e7/library/src/main/java/com/danielstone/materialaboutlibrary/model/MaterialAboutCard.java
[9]: https://github.com/daniel-stoneuk/material-about-library/blob/ce35cdc99d33af1faf40c7a1cddf2889898be4e7/library/src/main/java/com/danielstone/materialaboutlibrary/model/MaterialAboutTitleItem.java
[10]: https://github.com/daniel-stoneuk/material-about-library/blob/ce35cdc99d33af1faf40c7a1cddf2889898be4e7/library/src/main/java/com/danielstone/materialaboutlibrary/model/MaterialAboutActionItem.java
[11]: https://github.com/daniel-stoneuk/material-about-library/blob/ce35cdc99d33af1faf40c7a1cddf2889898be4e7/library/src/main/java/com/danielstone/materialaboutlibrary/model/MaterialAboutList.java

[101]: https://jitpack.io/v/daniel-stoneuk/material-about-library.svg
[102]: https://jitpack.io/#daniel-stoneuk/material-about-library
[103]: https://img.shields.io/github/license/HeinrichReimer/material-intro.svg
[104]: https://www.apache.org/licenses/LICENSE-2.0.html
