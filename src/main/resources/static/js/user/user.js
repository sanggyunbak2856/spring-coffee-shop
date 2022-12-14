let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        })
        $('#btn-delete').on('click', function () {
            _this.delete();
        })
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

        if(this.validationEmail(data.email) === false) {
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
                    console.log(JSON.stringify(error))
                }
                else {
                    alert(JSON.stringify(error));
                }
            });
        }
    },
    update : function () {
        let id = $('#id').val();
        let data = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        if(this.validationEmail(data.email) === false) {
            alert("이메일 형식이 잘못되었습니다")
        }
        else {
            $.ajax({
                type: 'PUT',
                url: '/api/v1/user/' + id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('유저가 수정되었습니다.');
                location.href = "/users";
            }).fail(function (error) {
                if(error.status == 404) {
                    alert("해당 아이디를 가진 유저가 없습니다.")
                    console.log(error)
                }
                else {
                    alert(JSON.stringify(error));
                }
            });
        }
    },
    delete : function () {
        let id = $('#id').val()
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/user/' + id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
            }).done(function() {
                alert('유저가 삭제되었습니다.');
                location.href = "/users";
            }).fail(function (error) {
                alert(JSON.stringify(error))
            });
    }
};

main.init();