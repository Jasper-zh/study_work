/**
 * by 木瓜煲鸡脚
 * 获取表单数据对象
 */
$.fn.serializeObject = function() {
  var o = {};
  var a = this.serializeArray();
  $.each(a, function() {
    if (o[this.name]) {
      if (!o[this.name].push) {
        o[this.name] = [ o[this.name] ];
      }
      o[this.name].push(this.value || '');
    } else {
      o[this.name] = this.value || '';
    }
  });
  return o;
  };
  
  /**
   * by 木瓜煲鸡脚
   * 删除数组给定元素
   * 
   * @param {数组} _arr 
   * @param {元素} _obj 
   */
  function removeAarry(_arr, _obj) {
  var length = _arr.length;
  for (var i = 0; i < length; i++) {
      if (_arr[i] == _obj) {
          if (i == 0) {
              _arr.shift(); //删除并返回数组的第一个元素
              return _arr;
          }
          else if (i == length - 1) {
              _arr.pop();  //删除并返回数组的最后一个元素
              return _arr;
          }
          else {
              _arr.splice(i, 1); //删除下标为i的元素
              return _arr;
          }
      }
  }
  } 