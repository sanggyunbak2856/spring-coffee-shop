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
    save : function () {
        let data = {
            address: $('#address').val(),
            userId: $('#userId').val()
        };

        if(data.address === "" || data.address === " ") {
            alert("주소를 입력해주세요")
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/order',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('주문이 등록되었습니다.');
                location.href = "/orders";
            }).fail(function (error) {
                if(error.status == 404) {
                    alert("주문 id가 잘못되었습니다.")
                    console.log(JSON.stringify(error))
                }
                else {
                    alert(JSON.stringify(error));
                }
            });
        }
    },
    update : function () {
        let id = $('#id').val()
        let data = {
            address: $('#address').val(),
            orderStatus: $('#orderStatus').val()
        };

        if(data.address === "" || data.address === " ") {
            alert("주소를 입력해주세요")
        }
        else {
            $.ajax({
                type: 'PUT',
                url: '/api/v1/order/' + id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('주문이 등록되었습니다.');
                location.href = "/orders";
            }).fail(function (error) {
                if(error.status == 404) {
                    alert("주문 id가 잘못되었습니다.")
                    console.log(JSON.stringify(error))
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
            url: '/api/v1/order/' + id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            location.href = "/orders";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};

main.init();