var Recharge = {}
// 47.104.70.72
//localhost:8080
var getData = 'http://47.104.70.72/Recharge/bankRecharge/'
var uploadData = 'http://47.104.70.72/Recharge/bankRecharge/recharge.do'


Recharge.getData = function (phoneNr) {
    if (phoneNr == undefined) {
        phoneNr = ''
    }
    if (!Recharge.validatePhoneNr(phoneNr)) {
        phoneNr = ''
    }

    mui('body').progressbar().show();
    mui.ajax(getData + phoneNr, {
        dataType: 'json',
        type: 'get',
        success: function (data) {
            //本地存储电话
            localStorage.setItem("phoneNr",phoneNr);
            mui('body').progressbar().hide();
            if (data.result) {
                $("#result").removeClass('not-show').html(data.data.mobileTelInfo.qCellCore + data.data.mobileTelInfo.operator)

                $("#callFare").empty();
                $("#callFare").append(showGridData(data.data.callfare));

                $("#dataFlow").empty();
                $("#dataFlow").append(showGridData(data.data.dataflow));

                $(".card").unbind('click').bind('click', function () {
                    // 组织数据
                    var operatorId = data.data.mobileTelInfo.operatorCode
                    var businessType = $(this).attr("businessType")
                    var packageId = $(this).attr("id")
                    var qCellCoreCode = data.data.mobileTelInfo.qCellCoreCode
                    var mobileTel = phoneNr
                    var packageName = $(this).attr("name")
                    var price = $(this).attr("price")

                    Recharge.uploadData(operatorId, businessType, packageId, qCellCoreCode, mobileTel, packageName, price)

                })
            } else {
                $("#result").addClass('not-show').html()
                // $("#operatorId").val()
                // $("#qCellCoreCode").val()
                mui.toast(data.message, { duration: 'long', type: 'div' })
            }
        },
        error: function (xhr, type, errorThrown) {
            mui('body').progressbar().hide();
            mui.toast('请求失败' + type, { duration: 'long', type: 'div' })
        }
    });
}

Recharge.uploadData = function (operatorId, businessType, packageId, qCellCoreCode, mobileTel, packageName, price) {


    //  此处可以进行优化显示
    var result = true
    result = Recharge.validateUploadData(mobileTel, '请输入手机号码')
    if (!result) return
        result = Recharge.validateUploadData(packageId, '包不存在，无法充值')
    if (!result) return
        result = Recharge.validateUploadData(businessType, '业务类型不存在，无法充值')
    if (!result) return
        result = Recharge.validateUploadData(operatorId, '运营商编号错误，请检查电话号码')
    if (!result) return
        result = Recharge.validateUploadData(qCellCoreCode, '归属地编号错误，请检查电话号码')
    if (!result) return

        var msgStr = '手机充值' + packageName
    if (businessType == 100) {
        msgStr += '话费 \n'
    }else{
        msgStr += '流量 \n'
    }

    msgStr += '¥' + price + '元'

    mui('body').progressbar().show();

    mui.confirm(msgStr,'支付',['取消','确定'],function(e) {
        mui('body').progressbar().hide();
        if (e.index == 1) {
            mui.ajax(uploadData, {
                data: {
                    packageId: packageId,
                        businessType: businessType, //业务类型，100话费，200流量
                        operatorId: operatorId, //运营商编号
                        qCellCoreCode: qCellCoreCode,//归属地编号
                        mobileTel: mobileTel//充值号码
                    },
                    dataType: 'json',
                    type: 'post',
                    contentType: "application/json",
                    success: function (data) {
                        if (data.result) {
                            mui.toast(data.message, { duration: 'long', type: 'div' })
                        } else {
                            mui.toast(data.message, { duration: 'long', type: 'div' })
                        }
                    },
                    error: function (xhr, type, errorThrown) {
                        mui.toast('请求失败' + type, { duration: 'long', type: 'div' })
                    }
                });

        }
    },'div')
}

// 组织界面展示
function showGridData(gridData) {
    var html = '';
    if (gridData instanceof Array) {
        gridData.map(d => {
            html += '<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">';
            html += '<div class="card" id=' + d.id + ' businessType=' + d.businessType + ' name= ' + d.name + ' price= ' + d.price + '>'

            if (d.packageType == 200) {
                html += '<span class="mui-badge mui-badge-danger">' + d.packageTypeStr + '</span>';
            }

            html += '<p class="name">' + d.name + '</p>'
            html += '<p class="price">售价' + d.price + '元</p>'
            html += '<p class="validTime">' + d.validTime + '</p>'
            html += '</div></li>'
        })
    }

    return html;
}

Recharge.validatePhoneNr = function (phoneNr) {
    var result = true
    if (phoneNr == '') {
        return false
    }
    if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(phoneNr))) {
        mui.toast('手机号码错误，请重新输入', { duration: 'long', type: 'div' })
        result = false
        $("#number-input").val('')
    } 
    return result
}

Recharge.validateUploadData = function (value, erroMsg) {
    var result = true
    if (value == null || value == "") {
        mui.toast(erroMsg, { duration: 'long', type: 'div' })
        result = false
    }
    return result
}


// 输入电话号码之后，进行请求
$("#number-input").blur(function () {
    Recharge.getData($(this).val());
})



$("#orderButton").click(function(){ 
    var phoneNr = $("#number-input").val()
    if (phoneNr == '') {
        mui.toast('请输入手机号码', { duration: 'short', type: 'div' })
        return
    }
    if (!Recharge.validatePhoneNr(phoneNr)) {
        return
    }
    
    mui.openWindow({
        url:'order.html',
        createNew:false,
        show:{
          autoShow:true,
      },
      waiting:{
          autoShow:true,
          title:'正在加载...'
      }
  })
});