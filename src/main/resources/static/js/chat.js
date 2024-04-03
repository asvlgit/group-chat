$(function(){
    let updateMessages = function(){
        $.get('/message',{},function(response){

        });
    };

    let initApplication = function(){
        $('.messages-and-users').css({display: 'flex'});
        $('.controls').css({display: 'flex'});
        $('.send-message').on('click', function(){
            $.post('/message',{message: message}, function(response){
                let message = $('.new-message').val();
                if(response.result){
                    $('.new-message').val('');
                } else {
                    alert('Ошибка. Повторите попытку позже...');
                }
            });
        });
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