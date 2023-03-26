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
    $("#newMessages").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

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
    stompClient.send("/app/hello", {}, JSON.stringify({'name': username}));
    Cookies.set('username',username);
}
function sendRoomName(){
    roomName=$("#roomName").val();
    stompClient.send("/app/join", {}, JSON.stringify({'roomName':  roomName}));
    Cookies.set("roomName",roomName)
}
function subscribeToRoom(){
    stompClient.subscribe('/topic/greetings/' + roomName ,function(message) {
    });
    let message = $("#message").val();
    let sendPath = '/app/join/' + roomName;

    stompClient.send(sendPath, {}, JSON.stringify({'message': message,'name':username}));


}

function showGreeting(message) {
    $("#Greeting").append("<tr><td>" + message + "</td></tr>");
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect();});
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName();sendRoomName();});
    $(function () {
        $("#send").click(function (event) {
            document.querySelector('#room-id-show').textContent=Cookies.get('roomName');
            event.preventDefault();
            let name = Cookies.get('username');
            let roomName = Cookies.get('roomName');
            if (name && roomName) {
                connect(name, roomName);
                $("#main-content").classList.add('hidden');
                $("#chat-page").classList.remove('hidden');
            } else {
                alert("Please enter your name and room name.");
            }
        });
    });
    $( "#sendMessage" ).click(function() {subscribeToRoom()});

});