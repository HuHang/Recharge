var Order = {}

var getData = 'http://47.104.70.72/Recharge/order'


Order.getData = function(phoneNr,state){
	if (phoneNr == undefined) {
		phoneNr = ""
	}
	if(state == undefined){
		state = ''
	}
	mui('body').progressbar().show();
	mui.ajax(getData + '?mobileTel=' phoneNr + '&state=' + state,{
		dataType: 'json',
		type: 'get',
		success: function(data){
			mui('body').progressbar().hide();
			if (data.result) {
				$("#order").empty();
				$("#order").append(showListData(data.data));
			}else{
				mui.toast(data.message, { duration: 'long', type: 'div' })
			}
		},
		error: function(xhr, type, errorThrown){
			mui('body').progressbar().hide();
			mui.toast('请求失败' + type, { duration: 'long', type: 'div' })
		}
	})
}

function showListData(listData) {
	var html = '';
	if (listData instanceof Array) {
		listData.map(d =>{
			html += '<li class="mui-table-view-cell mui-media">';
			html += '<div class="mui-card" id= '+ d.id +' businessTypeStr= '+d.businessTypeStr+' mobileTel= ' + d.mobileTel + 'name= ' + d.name + ' gmtCreate= ' + d.gmtCreate + ' serialNumber= ' + d.serialNumber + ' transactionSum= ' + d.transactionSum + '>'
			html += '<div class="mui-card-header businessTypeStr">' + d.businessTypeStr + '充值<p class="stateStr">' + d.stateStr +'</p></div>'

			html += '<div class="mui-card-content">'
			html += '<p class="phoneNr">充值号码：' + d.mobileTel + '</p>'
			html += '<p class="orderName">充值面额：' + d.packageName + '</p>'
			html += '<p class="gmtCreate">充值时间：' + d.gmtCreate + '</p>'
			html += '<p class="serialNumber">充值单号：' + d.serialNumber + '</p>'
			html += '<p class="transactionSum">合计：¥' + d.transactionSum + '</p></div>'

			// html += '<div class="mui-card-footer"><button type="button" class="mui-btn" align="right">再次充值</button></div>'

			html += '</div></li>'
		})
	}
	return html
}

mui.plusReady(function() {
	mui(".mui-scroll").on("tap", ".mui-control-item", function(e) {
		debugger
		var wid = this.getAttribute("data-wid");
		group.switchTab(wid);
	});

});