var stompClient = null;

var username = null;
var roomName = null;

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
    username=$("#name").val();
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    Cookies.set('username',username);
}
function sendRoomName(){
    roomName=$("#roomName").val();
    stompClient.send("/app/join", {}, JSON.stringify({'roomName':  $("#roomName").val()}));
    Cookies.set("roomName",roomName)
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
            document.querySelector('#room-id-display').textContent=Cookies.get('roomName');
            event.preventDefault();
            var name =Cookies.get('username');
            var roomName = Cookies.get('roomName');
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