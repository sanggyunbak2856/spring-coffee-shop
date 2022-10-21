let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        let data = {
            quantity: $('#quantity').val(),
            orderId: $('#orderId').val(),
            productId: $('#productId').val(),
        };

        if(data.quantity === 0) {
            alert("0개 이상 입력해주세요")
        }
        else if(data.orderId === "" || data.orderId === " ") {
            alert("주문 아이디를 입력해주세요")
        }
        else if(data.productId === "" || data.productId === " ") {
            alert("상품 아이디를 입력해주세요")
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/orderitem',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('주문 아이템이 등록되었습니다.');
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
    }
};

main.init();