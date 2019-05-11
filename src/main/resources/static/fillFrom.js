//自动填充json到form中
var fillForm = function ($form, json) {
    var jsonObj = json;
    if (typeof json === 'string') {
        jsonObj = $.parseJSON(json);
    }

    for (var key in jsonObj) {  //遍历json字符串
        var objtype = jsonObjType(jsonObj[key]); // 获取值类型

        if (objtype === "array") { //如果是数组，一般都是数据库中多对多关系

            var obj1 = jsonObj[key];
            for (var arraykey in obj1) {
                //alert(arraykey + jsonObj[arraykey]);
                var arrayobj = obj1[arraykey];
                for (var smallkey in arrayobj) {
                    setCkb(key, arrayobj[smallkey]);
                    break;
                }
            }
        } else if (objtype === "object") { //如果是对象，啥都不错，大多数情况下，会有 xxxId 这样的字段作为外键表的id

        } else if (objtype === "string") { //如果是字符串
            var str = jsonObj[key];
            var date = new Date(str);
            if (date.getDay()) {  //这种判断日期是本人懒，不想写代码了，大家慎用。
                $("[name=" + key + "]", $form).val(date.format("yyyy-MM-dd"));
                continue;
            }

            var tagobjs = $("[name=" + key + "]", $form);
            if ($(tagobjs[0]).attr("type") == "radio") {//如果是radio控件
                $.each(tagobjs, function (keyobj, value) {
                    if ($(value).attr("val") == jsonObj[key]) {
                        value.checked = true;
                    }
                });
                continue;
            }

            $("[name=" + key + "]", $form).val(jsonObj[key]);

        } else { //其他的直接赋值
            $("[name=" + key + "]", $form).val(jsonObj[key]);
        }

    }
};

var setCkb = function (name, value) {
    //alert(name + " " + value);
    //$("[name=" + name + "][value=" + value + "]").attr("checked", "checked");  不知为何找不到具体标签;
    $("[name=" + name + "][val=" + value + "]").attr("checked", "checked");
};


//根据td id自动匹配json数据
var fillTable = function ($form, data) {

    var length = data.length;       //json数据源
    var table = $form;              //表单id
    var ths = table.find('th');     //获取表单所有的th
    var thList = [];

    for (var i = 0; i < ths.length; i++) {
        thList.push(ths[i].id);     //获取th的id，要和json数据的字段名对应
    }
    var tbody = table.find('tbody');
    tbody.find('tr:first').remove();

    for (var i = 0; i < length; i++) {
        var tr = $("<tr></tr>");
        tr.appendTo(tbody);             //生成行

        for (var j = 0; j < thList.length; j++) {
            if (j === 0) {
                var xh = $("<td class='text-c'>" + (i + 1) + "</td>");     //序号列自增长
                xh.appendTo(tr);
                continue;
            }
            var field = thList[j];
            var val = "";
            if (field != null && field !== '') {
                val = data[i][field];       //根据th id去获取相应的json数据
            }
            if (field === 'orderType') {   //0-待揽件，1-已通知快递员，2-快递员已揽件，3-在途，4-配送中，5-已完成，9-作废
                switch (data[i][field]) {
                    case 0:
                        val = '待揽件';
                        break;
                    case 1:
                        val = '已通知快递员';
                        break;
                    case 2:
                        val = '快递员已揽件';
                        break;
                    case 3:
                        val = '在途';
                        break;
                    case 4:
                        val = '配送中';
                        break;
                    case 5:
                        val = '已完成';
                        break;
                    case 9:
                        val = '作废';
                        break;
                }
            }
            var td = $("<td class='text-c'>" + val + "</td>");
            td.appendTo(tr);            //生成数据项
        }
    }
    //删除分页插件
    // $("#table_info").remove();
    // $("#table_paginate").remove();
    // $("#table_length").css({"display": "none"});
    // $("#table_filter").css({"display": "none"});

};

//追加操作
var appendOperation = function ($form, data) {
    var len = data.length;
    var lastTr;
    for (var i = 0; i < len; i++) {
        var orderId = data[i]["orderId"];
        console.log(orderId);
        var lastTr = $form.find('tbody').find('tr').eq(i).find('td:last');
        var td = '<a style="text-decoration:none" class="ml-5" onClick="deleteCustomerSorder(this,'+ orderId +')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>';

        lastTr.attr('class', "f-14 td-manage text-c");
        lastTr.html(td);
    }
};


/** 表单序列化成json字符串的方法  */
function form2JsonString(formId) {
    var paramArray = $('#' + formId).serializeArray();
    /*请求参数转json对象*/
    var jsonObj = {};
    $(paramArray).each(function () {
        jsonObj[this.name] = this.value;
    });
    // json对象再转换成json字符串
    jsonObj = JSON.stringify(jsonObj);
    console.log(jsonObj);

    return jsonObj;
}

/** 表单序列化成json对象的方法  */
function form2JsonObject(formId) {
    var paramArray = $('#' + formId).serializeArray();
    /*请求参数转json对象*/
    var jsonObj = {};
    $(paramArray).each(function () {
        jsonObj[this.name] = this.value;
    });
    console.log(jsonObj);
    return jsonObj;
}


/* =====省市区（县）三级联动 =====start=====*/
var orderSenderPosition = $('#orderSenderPosition');
//select 对象
var province;
var city;
var country;

//省市区数据
var provinceData;
var cityData;
var countryData;

/*加载省份列表*/
function showProvince($province) {
    province = $province;
    if (province.find("option:last").index() > 1) {
        province.empty();
        province.append("<option >请选择省份</option>")
    }
    //初始化加载
    if (!provinceData || provinceData === null || provinceData === '') {
        $.ajax({
            type: 'POST',
            url: "../../public/provideProvinceData.json",
            async: false,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (result) {
                provinceData = '';
                provinceData = result;
            },
            error: function (errorThrown) {
                layer.msg('provideData Error!', {icon: 1, time: 1000});
            }
        });
    }
    var len = provinceData.length;
    for (var i = 0; i < len; i++) {
        var provOpt = document.createElement('option');
        provOpt.innerText = provinceData[i]['regionName'];
        provOpt.value = provinceData[i]['regionId'];
        province.append(provOpt);
    }
}

/*加载市列表*/
function showCity($city) {
    city = $city;

    if (city.find("option:last").index() > 1) {
        city.empty();
        city.append("<option >请选择城市</option>");

    }
    var provinceId = province.val();
    var region = {
        "regionId": provinceId
    };
    $.ajax({
        type: 'POST',
        url: "../../public/provideCityData.json",
        data: JSON.stringify(region),
        async: false,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            cityData = result;
        },
        error: function (errorThrown) {
            layer.msg('provideData Error!', {icon: 1, time: 1000});
        }
    });

    var len = cityData.length;
    for (var i = 0; i < len; i++) {
        var cityOpt = document.createElement('option');
        cityOpt.innerText = cityData[i]['regionName'];
        cityOpt.value = cityData[i]['regionId'];
        city.append(cityOpt);
    }
}

/*加载区县列表*/
function showCountry($country) {
    country = $country;

    if (country.find("option:last").index() > 1) {
        country.empty();
        country.append("<option >请选择县区</option>")
    }
    var cityId = city.val();
    var region = {
        "regionId": cityId
    };
    $.ajax({
        type: 'POST',
        url: "../../public/provideCountryData.json",
        async: false,
        data: JSON.stringify(region),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            countryData = result;
        },
        error: function (errorThrown) {
            layer.msg('provideData Error!', {icon: 1, time: 1000});
        }
    });
    var len = countryData.length;
    for (var i = 0; i < len; i++) {
        var countryOpt = document.createElement('option');
        countryOpt.innerText = countryData[i]['regionName'];
        countryOpt.value = countryData[i]['regionId'];
        country.append(countryOpt);
    }
}



function bindDataToSelect(data, $select) {
    console.log(data);
    $select.empty();
    var len = data.length;
    for (var i = 0; i < len; i++) {
        var option = document.createElement('option');
        option.innerText = data[i]['regionName'];
        option.value = data[i]['regionId'];
        $select.append(option);
    }
}
