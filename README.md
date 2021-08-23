# KotlinSyntheticMigrationEstimator
This is a tiny application to roughly estimate migration effort and may be to track progress for kotlin synthetic import migration.


## Why this exists?
I work on an application which has 150+ modules and my team owns few of them. When kotlin team depericated synthetic imports, we started planning for migration. To estimate migration effort I wrote his application.


## How to use it?
- Download this jar from here.
- run `java -jar ktsy.jar -p <"path/to/src/main/java"> -o "resultFileName"`
- It will create a csv file with the name you provided. Which will look something like this

|Name|Synthetic Imports  | WildcardImports |
|--|--|--|
| SomeViewFile | 21  |false |
| SomeOtherViewFile | 1  |true |

## Is this my idea?
Absolutely not. Few days back, I came across [this blog post](https://obvious.in/blog/estimating-the-android-architecture-migration-for-simple) and basically this application is a modified version of that.
