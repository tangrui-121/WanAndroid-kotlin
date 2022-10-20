import 'dart:ui';

import 'package:flutter/material.dart';

import 'AAA.dart';
import 'Routes.dart' as routes;
import 'RoundImage.dart' as roundImage;

void main() => runApp(MaterialApp(
      initialRoute: routes.homePage,
      onGenerateRoute: routes.onGenerateRoute,
    ));

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: _widgetForRoute(window.defaultRouteName), //根据传Activity过来的值打开相应页面
    );
  }

  Widget _widgetForRoute(String route) {
    switch (route) {
      case 'home':
        return MyHomePage(title: 'I am home');
      case 'tab1':
        return MyHomePage(title: 'I am tab1');
      case 'tab2':
        return SampleApp();
      case 'roundImage':
        return roundImage.SampleApp();
      default:
        return MyHomePage(title: 'I am home hot load');
    }
  }
}

class MyBottomNavigationBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
        iconSize: 35, //图标大小 35
        fixedColor: Colors.green, //图片颜色绿色
        type: BottomNavigationBarType.fixed, //item 固定显示
        items: [
          //设置了 3 个 子 item
          BottomNavigationBarItem(icon: Icon(Icons.home), label: "首页"),
          BottomNavigationBarItem(icon: Icon(Icons.category), label: "图片"),
          BottomNavigationBarItem(icon: Icon(Icons.settings), label: "设置")
        ]);
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
          child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text(
                'pushed :',
              ),
              SizedBox(width: 20),
              Text(
                '$_counter',
                style: Theme.of(context).textTheme.headline4,
              ),
            ],
          ),
          SizedBox(height: 20),
          OutlinedButton(
            onPressed: () {
              Navigator.pushNamed(context, routes.imagePage,arguments: 'hahh');
            },
            child: Text("去列表页"),
          ),
        ],
      )),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
