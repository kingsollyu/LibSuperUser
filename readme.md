# LibSuperUser

## 说明

[![](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![](https://jitpack.io/v/kingsollyu/LibSuperUser.svg)](https://jitpack.io/#kingsollyu/LibSuperUser)
[![](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

这个一个su命令行工具集

## 配置

### 添加仓库

```
repositories {
    maven { url 'https://jitpack.io' }
}
```

### 添加引用
使用最新版本[![](https://jitpack.io/v/kingsollyu/LibSuperUser.svg)](https://jitpack.io/#kingsollyu/LibSuperUser)
```
dependencies {
    compile 'com.github.kingsollyu:LibSuperUser:last-version'
}
```

## 使用

```java
try {
    Shell.Result result = null;

    result= Shell.Su.run("ls /asdfasdf");
    Log.d(TAG, "命令执行结束：" + result.getExitCode());

    result = Shell.Su.run("ls -al /system/build.prop");
    Log.d(TAG, "命令执行结束：" + result.getExitCode());

    result = Shell.Su.run("ping -c 4 www.baidu.com");
    Log.d(TAG, "命令执行结束：" + result.getExitCode());
} catch (IOException e) {
    Log.e(TAG, e.getMessage(), e);
}

try {
    Shell.Result result = null;
    result= Shell.Sh.run("ls /system/build.prop");
    Log.d(TAG, "命令执行结束：" + result.getExitCode());

    result= Shell.Sh.run("ls -al /sdafsdfasdf");
    Log.d(TAG, "命令执行结束：" + result.getExitCode());

    result= Shell.Sh.run("cat /proc/cpuinfo");
    Log.d(TAG, "命令执行结束：" + result.getExitCode());
} catch (Exception e) {
    Log.e(TAG, e.getMessage(), e);
}
```

## License

```
Copyright 2017 Sollyu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



