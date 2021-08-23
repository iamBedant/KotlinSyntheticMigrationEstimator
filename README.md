# KotlinSyntheticMigrationEstimator
This is a tiny application to roughly estimate migration effort and may be to track progress for kotlin synthetic import migration.


## Why this exists?
I work on an application which has 150+ modules and my team owns some of them. When kotlin team deprecated synthetic imports, we started planning for migration. To estimate migration effort I wrote this application. You can get a similar idea by using something like `grep` or `ag` but if you have too many modules it becomes tedious.  


## How to use it?
- Download the `ktsy.jar` file from [here](https://github.com/iamBedant/KotlinSyntheticMigrationEstimator/releases/tag/0.0.1).
- run `java -jar ktsy.jar -p "path/to/src/main/java" -o "resultFileName"`
- It will create a csv file with the name you provided. Which will look something like this

|Name|Synthetic Imports  | WildcardImports |
|--|--|--|
| SomeViewFile | 21  |false |
| SomeOtherViewFile | 1  |true |

## Is this my idea?
Absolutely not. Few days back I came across [this blog post](https://obvious.in/blog/estimating-the-android-architecture-migration-for-simple) and basically this application is a modified version of that idea.
