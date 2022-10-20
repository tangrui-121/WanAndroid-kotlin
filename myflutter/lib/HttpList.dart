//导包
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class HttpPracticePage extends StatefulWidget {
  @override
  _HttpPracticePageState createState() => _HttpPracticePageState();
}

class _HttpPracticePageState extends State<HttpPracticePage> {
  var list = [];

  @override
  void initState() {
    super.initState();
    //加载网络数据
    _getData();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text('Http 实践页面'),
          ),
          //如果 list 为空，展示为空白页面，不为空渲染 ListView
          body: list.isNotEmpty
              ? ListView.builder(
              itemCount: list.length,
              itemBuilder: (context, index) {
                return ListTile(
                    title: Text("${list[index]["name"]}"),
                    subtitle: Text("${list[index]["link"]}"));
              })
              : Text("")),
    );
  }

  //使用 async 标记为异步
  void _getData() async {
    //构建请求 uri
    var uri = Uri.https("www.wanandroid.com", "/friend/json");
    //使用 http 发起 get 请求，等待返回结果进行后续代码执行
    var result = await http.get(uri);
    if (result.statusCode == 200) {
      //将 json 解析成 map
      var map = json.decode(result.body) as Map<String, dynamic>;
      //通知 UI 进行刷新
      setState(() {
        list = map["data"];
      });
    }
  }
}