# Git

#### 名词解释

仓库(<font color=#FF0000>Repository</font>)：保存了所有文件的修改历史

工作区(<font color=#FF0000>Working Directory</font>)：本地电脑上的工作目录文件夹  

暂存区(<font color=#FF0000>Staging area</font>)（索引）：临时存放想要修改的文件  

- 暂存区的用处：在命令行下选择自己想要提交的修改很麻烦，所以设置一个暂存区来存放想要修改的文件，最后再一次性提交到版本库

提交(<font color=#FF0000>Commit</font>)：将暂存区的文件提交到仓库  

冲突(<font color=#FF0000>Conflict</font>)：多人对同一文件同一部分进行了修改，导致冲突  

分支(<font color=#FF0000>Branch</font>)：当前分支分离的副本  

合并(<font color=#FF0000>Merge</font>)：对应分支，合并指定分支到当前分支头  

头(<font color=#FF0000>HEAD</font>)：指向当前分支  

标记(<font color=#FF0000>Tags</font>)：标记某一个分支的某一个版本

推送(<font color=#FF0000>Push</font>)：向远程仓库推送当前分支

拉取(<font color=#FF0000>Pull</font>)：向远程仓库拉文件，并合并到当前分支

已跟踪文件：指被纳入git版本控制的文件



#### 初始化一个文件夹为本地仓库

git init

![image-20200916171528491](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916171528491.png)



#### 克隆远程服务器到本地工作区

 git clone [地址]

#### 更新操作

分为四步

- git status：查看相关文件（工作区和暂存区）的状态

- git add . :把工作时的所有变化提交到暂存区，不包括被删除的文件

  1. git add -u .：将已跟踪文件中的修改和删除的文件添加到暂存区，不包括新增加的文件
  2. git add -A .：将已跟踪文件中的修改、删除的文件和新增的未跟踪文件添加到暂存区

- git commit -m "备注”：提交暂存区的文件到本地版本库

- git push：把本地版本库的分支推送到远程服务器对应的分支上

  示例（github）：

  查看相关文件状态+变化提交到暂存区+提交到暂存区的文件到本地版本库：

  ![image-20200916163940115](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916163940115.png)

  从本地版本库分支推送到远程服务器对应的分支上：

  ![image-20200916164040741](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916164040741.png)

  ![image-20200916164243037](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916164243037.png)

  成功把修改后的README.md文件更新到远程服务器（增加了test字段）

  

#### 分支

git branch -a：列出所有分支

git branch [branch_name]：创建分支

git checkout [branch_name]：切换分支

git merge [branch_name]：合并分支

git branch -d [branch_name]：删除分支



示例：

- 列出所有分支：

  ![image-20200916164740121](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916164740121.png)

- 创建名字为本人姓名全拼的分支并切换到该分支：

  ![image-20200916164839481](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916164839481.png)

- 和更新远程服务器一样的操作：变化提交到暂存区然后提交到版本库最后再推送到对应的分支

  ![image-20200916165047874](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916165047874.png)

  ![image-20200916165830391](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916165830391.png)

成功创建分支并更新

分支下存在一个多的test.txt文件

现在需要合并到master：

![img](file:///C:\Users\user\AppData\Roaming\Tencent\Users\2608953482\QQ\WinTemp\RichOle\%HJ2WGDJD`MN7Z{JM_G51NJ.png)



![image-20200916170009777](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200916170009777.png)

成功合并





#### Git Reset 三种模式

有时用commit提交后，发现有些内容是错误的，除了可以重新修改再commit一次之外（会多一次commit记录），还可以用git reset  

git commit ：可以让HEAD这个指针指向其他的地方



文件存入本地版本库流程：

1. 刚开始工作区、暂存区和本地版本库(HEAD)里的内容是一样的

   工作区-->暂存区-->本地版本库

2. 当git管理的文件夹里面的内容出现变化后，此时工作区与暂存区和本地版本库的内容不一样，git会标记修改后的文件状态为modified

   <font color=#FF0000>工作区</font>-->暂存区-->本地版本库

3. 执行git add后，会讲改变后的文件内容加入暂存区，这个时候，工作区和暂存区内容一致，但是与本地版本库不一致

   <font color=#FF0000>工作区</font>--><font color=#FF0000>暂存区</font>-->本地版本库

4. 执行了git commit后，暂存区的改变的文件内容会提交到本地版本库，建立新的commit节点(HEAD)后，三部分内容达到一致

   <font color=#FF0000>工作区</font>--><font color=#FF0000>暂存区</font>--><font color=#FF0000>本地版本库</font>

它有三种模式：soft,mixed,hard

- reset --hard：重置暂存区和工作区

  暂存区和工作区会被完全置换为和HEAD的新位置相同的内容

  

  