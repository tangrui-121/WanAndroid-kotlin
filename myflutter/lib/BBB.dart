import 'package:http/http.dart' as http;

// void main() {
//   var url = "https://www.baidu.com/";
//   //调用 get 函数请求 url, 返回一个封装了 http 请求任务的 future 对象
//   Future fTask = http.get(Uri.parse(url));
//   //打印 future 对象
//   print(fTask);
//
//   // 向 future 对象注册回调函数，处理请求结果
//   fTask.then((response) => {
//     print('Response status: ${response.statusCode}')
//   });
//   // 打印 main 函数结束标记
//   print('main end...');
// }

void main() async{
  var url = "https://www.baidu.com/";
  //请求 url, 通过 await，等待 future 异步计算任务的结果，执行成功就直接返回结果
  var response = await http.get(Uri.parse(url));
  print('Response status: ${response.statusCode}');
  print('main end...');
}