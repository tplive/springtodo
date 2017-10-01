function send() {
    // AJAX KODE HER
    var text = document.getElementById("textInput").value;
    var start = document.getElementById("startInput").value;
    var end = document.getElementById("endInput").value;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            console.log();
        }
    }
    xmlHttp.open("POST", "/save", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("text=" + text + "&start=" + start + "&end=" + end);
}

function refreshList() {
    var data = $.getJSON("/index/json", function (data) {

        var tr;
        $.each(data, function (i, val) {

            console.log(i.name);
            tr = $('<tr/>');
            tr.append('<td>' + data.name + '</td>');
            tr.append('<td>' + data.start + '</td>');
            tr.append('<td>' + data.end + '</td>');
        });


    });

}

function del(y) {

    var url = "/delete/?id=" + y;

    $.ajax({
        url: url,
        type: 'GET',
        success: function(result) {
            console.log("Delete" + url)
        }
    });
}


function update(x) {

    var url = "/update/?id=" + x;
    var text = document.getElementById("textInput").value;
    var start = document.getElementById("startInput").value;
    var end = document.getElementById("endInput").value;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            console.log();
        }
    }
    xmlHttp.open("PUT", url, true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send("text=" + text + "&start=" + start + "&end=" + end);
    console.log("URL: " + url);
}

$(document).ready(function () {
    var now = new Date();

    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);

    var today = now.getFullYear() + "-" + (month) + "-" + (day);


    $('#startInput').val(today);
    $('#endInput').val(today);

    $('#todos tr').click(function() {
        var href = $(this).find("a").attr("href");
        console.log(href);
        if(href) {
            window.location = href;
        }
    });

});
