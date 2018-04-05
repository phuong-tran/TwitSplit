# Assignment - ​ Project TwitSplit


## Description

The product Tweeter allows users to post short messages limited to 50
characters each.

Sometimes, users get excited and write messages longer than 50 characters.

Instead of rejecting these messages, we would like to add a new feature that will
split the message into parts and send multiple messages on the user's behalf,
all of them meeting the 50 character requirement.

### Example

#### Suppose the user wants to send the following message:

"*I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.*"

#### This is 91 characters excluding the surrounding quotes. When the user presses send, it will send the following messages:

*"1/2 I can't believe Tweeter now supports chunking"
"2/2 my messages, so I don't have to do it myself."*

#### Each message is now 49 characters, each within the allowed limit.


# Solution for Android Application

### IDE / Programing Language
	1) Android Studio
	2) Programing Language: Kotlin

## Technologies and 3rd party libraries
	
#### Android Support Libraries

	com.android.support:support-annotations
	com.android.support:appcompat-v7
	com.android.support:recyclerview-v7
	com.android.support:cardview-v7
	com.android.support:design
	com.android.support:support-v4
	com.android.support.constraint:constraint-layout
	
#### Data binding
	 com.android.databinding:compiler

#### ROOM
	android.arch.persistence.room:runtime
	android.arch.persistence.room:compiler
	android.arch.persistence.room:rxjava2

#### LIFECYCLE

	android.arch.lifecycle:extensions
	android.arch.lifecycle:common-java8
	android.arch.lifecycle:compiler

#### Dagger
	com.google.dagger:dagger
	com.google.dagger:dagger-android
	com.google.dagger:dagger-android-support
	com.google.dagger:dagger-compiler
	com.google.dagger:dagger-android-processor

#### RxJava, RxAndroid:

	io.reactivex.rxjava2:rxjava
	io.reactivex.rxjava2:rxandroid

#### EventBus (Optional)
	org.greenrobot:eventbus


###  Architecture Pattern

	 1) Model-View-ViewModel Pattern (MVVM) + Data binding
		Android Architecture Components
	  
	 2) DI - Dependency injection with Dagger 2

	 
## Message Splitter Algorithm

#### Step 1:
	Estimate number of line:
	
     1) originalLines = text.length / 50 + if (text.length % 50 > 0) 1 else 0
	   - Assumption: originalLines = 10
     
     2)  Generate indicator part per each line: 
	     indicatorString = "1/10 " + .... +  "10/10 "
	 
	 3)  Calculate indicator lines 
	     indicatorLines = indicatorString.length / 50  
      
	 4)  estimate = originalLines + indicatorLines

#### Step 2

	Split the input text into an array of words
	
	
#### Step 3

	Make a for loop that runs from i = 1 to i = 10
	Inside the loop

	- Assumption: i = 1
	
	Generate Indicator :  indicator = "1/10 " 
	
	indicator (x) + string item in "words" (y) so that x + y < = 50

	save the index of words in an variable for the next use


### Step 4

	After running end of the loop we check if index < number of words item,
	it means that the "estimate" is not correct, we need to estimate again
	
	1) Get remaining string
	2) Estimate remaining lines base on remaining string
	3) estimate = estimate + remaining lines
	4) Do it again (Recursive)

Check out : [**TwitSplitString.kt**](https://github.com/phuong-tran/TwitSplit/blob/master/app/src/main/java/zalora/com/twitsplit/utils/TwitSplitString.kt) for more details
		


## Demo


### Screenshots

![Demo1](https://github.com/phuong-tran/TwitSplit/blob/master/demo/Demo1.png)


![Demo2.1](https://github.com/phuong-tran/TwitSplit/blob/master/demo/Demo2.1.png)


![Demo2.2](https://github.com/phuong-tran/TwitSplit/blob/master/demo/Demo2.2.png)

	

### Video

[Video](https://github.com/phuong-tran/TwitSplit/blob/master/demo/video.mp4)
		



	
	
	
	 

		

	

	
	

		

