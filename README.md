# CookBook
个人开源Android项目:健康菜谱

## How does it look like?

* First

![](./first.png)

* Second

![](./second.png)

* Third

![](./third.png)

不好意思，图片有点大！


## Related content

compileSdkVersion 23
buildToolsVersion "23.0.2"

## dependencies

dependencies {
compile fileTree(include: ['*.jar'], dir: 'libs')
testCompile 'junit:junit:4.12'
compile 'com.android.support:appcompat-v7:23.1.0'
compile files('libs/gson-2.3.1.jar')
compile files('libs/Volley.jar')
compile files('libs/universal-image-loader-1.9.5.jar')
compile project(':library')

}

## Enjoy



