let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    validationEmail : function (email) {
        var regex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g
        return regex.test(email)
    },
    save : function () {
        let data = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        if(this.validationEmail(email) === false) {
            alert("이메일 형식이 잘못되었습니다")
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/user',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('유저가 등록되었습니다.');
                location.href = "/users";
            }).fail(function (error) {
                if(error.status == 409) {
                    alert("이미 해당 이메일을 사용하는 유저가 있습니다.")
                    console.log(error)
                }
                else {
                    alert(error);
                }
            });
        }
    }
};

main.init();