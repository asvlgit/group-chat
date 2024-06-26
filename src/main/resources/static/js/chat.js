$(function(){

    let updateUsersAndMessages = function(){
        updateUsers();
        updateMessages();
    }

    let getMessageElement = function(message){
        let item = $('<div class="message-item"></div>');
        let header = $('<div class="message-header"></div>');
        header.append($('<div class="datetime">' + message.datetime + '</div>'));
        header.append($('<div class="username">' + message.username + '</div>'));
        let textElement = $('<div class="message-text"></div>');
        textElement.text(message.text);
        item.append(header, textElement);
        return item;
    }

    let getUserElement = function(user){
        let item = $('<div class="user-item"></div>');
        item.append($('<div class="username">' + user.username + '</div>'));
        return item;
    }

    let updateMessages = function(){
        $.get('/message',{},function(response){
            if(response.length == 0){
                $('.messages-list').html('<i>Сообщений нет</i>');
                return;
            }
            $('.messages-list').html('');
            for(i in response){
                let element = getMessageElement(response[i]);
                $('.messages-list').append(element);
            }
        });
    };

    let updateUsers = function(){
        $.get('/user',{},function(response){
            if(response.length == 0){
                $('.users-list').html('');
                return;
            }
            $('.users-list').html('');
            for(i in response){
                let element = getUserElement(response[i]);
                $('.users-list').append(element);
            }
        });
    };

    let initApplication = function(){
        $('.messages-and-users').css({display: 'flex'});
        $('.controls').css({display: 'flex'});
        $('.new-message').focus();
        $('.send-message').on('click', function(){
            let message = $('.new-message').val();
            $.post('/message',{message: message}, function(response){
                if(response.result){
                    $('.new-message').val('');
                    updateMessages();
                } else {
                    alert('Ошибка. Повторите попытку позже...');
                }
            });
        });

        setInterval(updateUsersAndMessages, 1000);
    };

    let registerUser = function(name){
        $.post('/auth', {name: name}, function(response){
            if(response.result){
                initApplication();
            }
        });
    };

    $.get('/init',{}, function(response){
        if(!response.result){
            let name = prompt('Введите Ваше имя:');
            registerUser(name);
            return;
        }
        initApplication();
    });

});