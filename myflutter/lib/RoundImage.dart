import 'package:flutter/material.dart';

void main() {
  runApp(const SampleApp());
}

class SampleApp extends StatelessWidget {
  const SampleApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text('Flutter network image'),
            elevation: 30,
          ),
          body: MyBodyPage1()),
    );
  }
}

class MyBodyPage1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        Row(
          children: [
            MyBodyPage(),
            MyBodyPage(),
          ],
        ),
        Expanded(
          flex: 1,
          child: MyBodyPageListView(),
        )
      ],
    );
  }
}

class MyBodyPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ClipOval(
      //圆形图片
      child: Image.network(
        "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg",
        width: 100,
        height: 100,
        fit: BoxFit.cover,
      ),
    );
  }
}

class MyBodyPageListView extends StatelessWidget {
//mock 数据：相当于 List<Map>
  var mDataList = [
    {
      "title": "标题1",
      "desc": "描述1",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
    {
      "title": "标题2",
      "desc": "描述2",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
    {
      "title": "标题3",
      "desc": "描述3",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
    {
      "title": "标题4",
      "desc": "描述4",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
    {
      "title": "标题5",
      "desc": "描述6",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
    {
      "title": "标题5",
      "desc": "描述6",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
    {
      "title": "标题5",
      "desc": "描述6",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
    {
      "title": "标题5",
      "desc": "描述6",
      "image": "https://img.lianzhixiu.com/uploads/210106/37-21010609363aS.jpg"
    },
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        shrinkWrap: true, //范围内进行包裹（内容多高ListView就多高）
        //通过 ListView.builder 实现数据的动态加载
        itemCount: mDataList.length, //item 的个数
        itemBuilder: (context, index) {
          //通过 itemBuilder 构建 Widget
          return Column(
            children: [
              //Column 里面包装了 ListTile + Divider
              ListTile(
                leading: ClipOval(
                  //圆形图片
                  child: Image.network(
                    mDataList[index]["image"] ?? "",
                    width: 50,
                    height: 50,
                    fit: BoxFit.cover,
                  ),
                ),
                title: Text(mDataList[index]["title"] ?? ""), //标题
                subtitle: Text(mDataList[index]["desc"] ?? ""), //副标题
              ),
              Divider() //横线
            ],
          );
        });
  }
}
