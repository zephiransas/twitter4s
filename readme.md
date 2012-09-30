# Twitter4S

[Twitter4J](https://github.com/yusuke/twitter4j)のScalaラッパです。
Twitter4Jの基本的な機能をScalaである程度書きやすいようにラップしています。

## 利用方法

現状、jarに固めた形での配布は行っていません。

sbtにこのリポジトリに対する依存性を追加するか、ソースをダウンロードしてビルドして下さい。

## ビルド環境について

Twitter4Sは以下の環境で構築されています。

* Scala 2.9.1
* sbt 0.11.0
* Twitter4J 2.2系

## Twitter4Jとの依存関係

Twitter4Sのビルド依存性にTwitter4Jのライブラリを含めています。

2012.09.30現在、Twitter4J 2.2系を依存するライブラリとしてビルドします。

## サンプルについて

現状、テストコードしかサンプルに相当する物はありません。

基本的な利用の仕方は、テストコードをご参照ください。