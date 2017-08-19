/**
 * Created by isghost on 2017/8/13.
 */
let searchParams = new URLSearchParams(window.location.search)
let urlType = searchParams.get("type") || "other"
$(function(){
    // https://developer.mozilla.org/en-US/docs/Web/API/URLSearchParams
    let reqUrl = "/image/otherCollection.json";
    if(urlType == "self"){
        reqUrl = "/image/collections.json";
    }
    $.ajax({
        type: "POST",
        data: "pageNum=" + (searchParams.get("pageNum") || 1),
        url: reqUrl,
        success: fillPage
    })

});

/**
 * 填充界面
 */
function fillPage(result){
    showImage(result);
    showPagination(result);
}
/**
 * 显示图片
 * @param result
 */
function showImage(result){
    let baseUrl = result["baseUrl"];
    let imageUrl = result["imageUrl"];
    for(let i = 0;i<imageUrl.length;i++){
        $("#templetCeil").clone().insertBefore("#templetCeil")
            .css("display", "inline-block")
            .attr("src", baseUrl + imageUrl[i])
            .attr("id", "ceil" + i)
    }
}

function showPagination(result){
    let curPageNum = searchParams.get("pageNum") || 1;
    curPageNum = parseInt(curPageNum);
    let maxPage = result.maxPage;
    const halfSize = 4;
    let showMinPage = 1;
    let showMaxPage = 1;
    if(curPageNum - halfSize < 1){
        showMinPage = 1;
        showMaxPage = Math.min(showMinPage + halfSize * 2, maxPage);
    }
    else if(curPageNum + halfSize > maxPage){
        showMaxPage = maxPage;
        showMinPage = Math.max(showMaxPage - halfSize * 2, 1);
    }
    else{
        showMinPage = curPageNum - halfSize;
        showMaxPage = curPageNum + halfSize;
    }
    let url = window.location.href.split('?')[0] + "?type=" + urlType + "&pageNum=";
    for(let i = showMinPage; i <= showMaxPage; i++){
        $("#page").clone().insertBefore("#page")
            .attr("display",  "inline-block")
            .attr("id", "page" + i)
            .find("a").text(i)
            .attr("href", url + i);
        if(i == curPageNum){
            $("#page" + i).addClass("active");
        }

    }

    $("#loading").css("display", "none");

    $("#prePage").attr("href", url + Math.max(curPageNum - 1, 1));
    $("#nextPage").attr("href", url + Math.min(curPageNum + 1, showMaxPage));
    disableUrl($("#prePage"), Math.max(curPageNum - 1, 1) == curPageNum);
    disableUrl($("#nextPage"), Math.min(curPageNum + 1, showMaxPage) == curPageNum);
}

function disableUrl(ele, disable){
    if(disable){
        ele.parent().addClass("disabled");
    }
}

