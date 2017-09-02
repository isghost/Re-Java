/**
 * Created by isghost on 2017/9/2.
 */

$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            console.log(data.result);
            let newItem = $("#statusItemTemp").clone().insertBefore("#statusItemTemp")
                .attr("id", Math.random())
                .css("display", "flex")
            newItem.find(".imageName").text(data.result.originalName);
            if(data.result.result == "true"){
                newItem.find(".imageStatusDesc").text("上传成功").css("color", "#00cc11");
                newItem.find("img").attr("src", data.result.imageUrl);
            }
            else{
                newItem.find(".imageStatusDesc").text("上传失败").css("color", "#aa3333");
                newItem.find("img").attr("src", data.result.imageUrl);
            }

        }
    });
});
