#!/usr/bin/env bash
########################################################################################################################
# 功能: 安装springboot-side项目到本地maven仓库
# 作者: 应卓
# 日期: 2016-06-09
########################################################################################################################

git clone https://github.com/yingzhuo/springboot-side.git
mvn -f springboot-side/pom.xml clean install
rm -rf springboot-side/
