#!/usr/bin/env bash
########################################################################################################################
# 功能: 安装springboot-side项目到本地maven仓库
# 作者: 应卓
# 日期: 2016-06-17
########################################################################################################################

cd /tmp

# 删除项目
if [ -d springboot-side ]; then
    rm -rf springboot-side/
fi

# 删除日志
if [ -f springboot-side-install.log ]; then
    rm springboot-side-install.log
fi

# 克隆项目
git clone https://github.com/yingzhuo/springboot-side.git | tee -a springboot-side-install.log

# 安装
mvn -f ./springboot-side/pom.xml clean install -U -Dmaven.test.skip=true | tee -a springboot-side-install.log

# 删除项目
rm -rf springboot-side/

cd -

# 结束
exit 0
