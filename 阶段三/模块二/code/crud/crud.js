/**
 * 模拟数据
 */
let tableData = [
    {
      code:'A001',
      name:'Jasper',
      sex:'男',
      password:'xxxxxx',
      age:20,
      birthday:'1999-02-23'
    },
    {
      code:'A002',
      name:'木瓜',
      sex:'男',
      password:'xxxxxx',
      age:20,
      birthday:'1999-09-04'
    },
    {
      code:'A003',
      name:'alen',
      sex:'男',
      password:'xxxxxx',
      age:20,
      birthday:'2006-12-24'
    },
  ]

let queryList = []

let deletes = []  

let updateOrSave = 0

/**
 * by 木瓜煲鸡脚
 * 初始化方法
 */
$(function(){
  // 1.发送请求获取数据
  // 2.成功获取数据初始化表格
  load(tableData)
});

/**
 * by 木瓜煲鸡脚
 * 渲染数据表
 * 
 * @param {数据} data 
 */
function load(data){
  // 1.拿到表格数据体并清空
  let tbody = $("#mytable tbody")
  tbody.empty();

  // 2.遍历拼接各列标签，写入tbody
  data.forEach(item => {
    var checkItemTd = $('<td><input type="checkbox" class="check" name="checkItem" value="'+ item.code +'"/></td>');  //选框
    // 各列信息
    var code = $("<td></td>").append(item.code);
    var name = $("<td></td>").append(item.name);
    var sex = $("<td></td>").append(item.sex);
    var password = $("<td></td>").append(item.password);
    var age = $("<td></td>").append(item.age);
    var birthday = $("<td></td>").append(item.birthday);
    // 放入行中
    var row = $("<tr></tr>").append(checkItemTd).append(code).append(name).append(sex).append(password).append(age).append(birthday); 
    // 最终将本项放入tbody
    row.appendTo(tbody);
  });
}

/**
 * by 木瓜煲鸡脚
 * 复选框事件
 * 注意：
 * 监听对象要是初始化之前就已存在的节点，
 * 因此这里用了$(document)而不是$('.check')
 */
$(document).on('click','.check',function(){
    if($(this).is(':checked')){
      console.log("选中了，push")
      deletes.push($(this).val())
      console.log(deletes)
    }else {
      console.log('移出了，remove')
      // if($.inArray($(this).val(), deletes) != -1){
      //   deletes.splice($.inArray($(this).val(), deletes), 1);
      //   console.log(deletes);
      // }
      removeAarry(deletes,$(this).val());
      console.log(deletes);
    }
  });

/**
 * by 木瓜煲鸡脚
 * 保存or更新 数据项
 */  
function save_user(){
    if(updateOrSave == 1){ //更新
      tableData.forEach(item => {
        if(item.code == deletes[0]){
          item.name = $('.modal-body [name="name"]').val();
          item.sex = $('.modal-body [name="sex"]').val();
          item.password = $('.modal-body [name="password"]').val();
          item.age = $('.modal-body [name="age"]').val();
          item.birthday = $('.modal-body [name="birthday"]').val();
        }
      });
      deletes = [];
      updateOrSave = 0;
    }else{ //添加
      var form_data = $("#form_data").serializeObject();
      tableData.push(form_data);
    }
    //修改完毕重置表单
    $("#form_data")[0].reset();
    //关闭窗口
    $("#myModal").modal('hide');
    //重新加载数据表
    load(tableData);
}

/**
 * by 木瓜煲鸡脚
 * 
 * 删除选中项
 */
function delete_user(){
  if(deletes.length == 0){
    confirm("您还没选择要删除的项哦！")
    return;
  }
  if(confirm("确定删除勾选内容?")){
    deletes.forEach(del => {
      tableData.forEach(item => {
        if(item.code == del){
          removeAarry(tableData,item);
        }
      });
    });
    //删除完后清空选择列表
    deletes = []
    load(tableData)
  }
}

/**
 * by 木瓜煲鸡脚
 * 触发updateOrSave编辑窗口
 */
function openSave(){
  updateOrSave = 1;
  if(deletes.length != 1){
    confirm("更新操作有且仅能选择一条");
    return;
  }
  var code = deletes[0];
  tableData.forEach(item => {
    if(item.code == code){
      $('.modal-body [name="code"]').val(item.code)
      $('.modal-body [name="name"]').val(item.name)
      $('.modal-body [name="sex"]').val(item.sex)
      $('.modal-body [name="password"]').val(item.password)
      $('.modal-body [name="age"]').val(item.age)
      $('.modal-body [name="birthday"]').val(item.birthday)
    }
  });
  // 更新操作：禁用工号输入
  $('.modal-body [name="code"]').attr("disabled","disabled")
  $("#myModal").modal('show');
}

/**
 * by 木瓜煲鸡脚
 * 
 * 关闭编辑窗口
 */
function closeSave(){
  // 没有更新操作直接关闭了也要记得清空
  $("#form_data")[0].reset();
}

/**
 * by 木瓜煲鸡脚
 * 条件查询
 */
function search(){
  // 1.获取查询参数
  var code = $('#query1').val();
  var name = $('#query2').val();
  // 2.清空查询列表
  queryList = [];
  tableData.forEach(item => {
    if(code && name && item.code == code && item.name == name){
      queryList.push(item)
    }else if(!code && name && item.name == name){
      queryList.push(item);
    }else if(code && !name && item.code == code){
      queryList.push(item)
    }else if(!code && !name){
      queryList.push(item)
    }
  });
  load(queryList);
} 
