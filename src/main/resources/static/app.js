var stompClient = null;

var chatPage=document.querySelector('#chat-page');
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendRoomName(){
    stompClient.send("/app/join", {}, JSON.stringify({'roomName': $("#roomName").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName();sendRoomName()});
    $(function () {
        // ...

        // send button click event
        $("#send").click(function (event) {
            event.preventDefault();
            var name = $("#name").val();
            var roomName = $("#roomName").val();
            if (name && roomName) {
                connect(name, roomName);
                $("#main-content").addClass("hidden");
                $("#chat-page").removeClass("hidden");
            } else {
                alert("Please enter your name and room name.");
            }
        });
    });

});