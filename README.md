Android Constrained Widgets
===========================

This library offers a set of widgets that will respect a given aspect ratio while allowing to set 
relative dimensions. For instance, you can have a layout that extends to the whole available width 
but whose height will be adjusted to always keep a square shape.

## Demo

A demo of the widget is worth a thousand words. You can download it for free on Google Play.

<a href="https://play.google.com/store/apps/details?id=com.marvinlabs.widget.constrained.demo">
  <img alt="Demo on Google Play"
         src="http://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
</a>

## Usage 

The easiest way to get the library included in your project is by using Gradle. Simply add the 
following line to your dependencies block:

    dependencies {
        compile 'com.marvinlabs:android-constrained-widgets:1.2.+@aar'
    }
    
Of course, you can replace the version number by whichever version you need (you can have a look at 
this repository's tags to know which is the latest).

You can then integrate the widgets in your layouts in XML like that:

    <!-- Define the app namespace in the root layout: 
          xmlns:app="http://schemas.android.com/apk/res-auto" -->

    <!-- Below is a 16/9 image that will use the whole available width -->

    <com.marvinlabs.widget.aspectratio.ConstrainedImageView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:scaleType="centerCrop"
            app:aspectRatioWidth="16"
            app:aspectRatioHeight="9"
            app:fixedDimension="width">

## Changelog

### 1.2.0 

  - [new] Constrained image view (to give your image views a consistent aspect ratio)
  - [fix] AspectRatioDelegate.onMeasure has been made public so that you can use it for your own 
    views
  - [fix] Gradle build scripts improved to make it easier to build (does not require signing files, 
    ...) 

### 1.0.0 

  - Initial release
  - [new] 3 constrained layouts: FrameLayout, LinearLayout and RelativeLayout 

## About Vincent & MarvinLabs

I am a freelance developer located in Biarritz, France. You can 
[have a look at my website](http://vincentprat.info) to get to know me a little better. If you want 
to follow me, here are some links:

* [Follow me on Twitter](http://twitter.com/vpratfr)
* [Follow me on Google+](https://plus.google.com/+VincentPrat)
* [Follow me on Facebook](http://www.facebook.com/vpratfr)

MarvinLabs is my digital studio specialised in native mobile applications and web sites. You can 
[browse our website](http://www.marvinlabs.com) to get to know us a little better. If you want to 
get updates about our work, you can also:

* [Follow us on Twitter](http://twitter.com/marvinlabs)
* [Follow us on Google+](https://plus.google.com/+Marvinlabs)
* [Follow us on Facebook](http://www.facebook.com/studio.marvinlabs)
