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
            name: $('#name').val(),
            price: $('#price').val(),
            quantity : $('#quantity').val(),
            description : $('#description').val()
        };

        if(data.name === "" || data.description === "") {
            alert("칸을 모두 채워주세요")
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/product',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('상품이 등록되었습니다.');
                location.href = "/products";
            }).fail(function (error) {
                alert(JSON.stringify(error))
            });
        }
    },
    update : function () {
        let id = $('#id').val()
        let data = {
            name: $('#name').val(),
            price: $('#price').val(),
            quantity : $('#quantity').val(),
            description : $('#description').val()
        };

        if(data.address === "" || data.address === " ") {
            alert("주소를 입력해주세요")
        }
        else {
            $.ajax({
                type: 'PUT',
                url: '/api/v1/product/' + id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('상품이 수정되었습니다.');
                location.href = "/products";
            }).fail(function (error) {
                if(error.status == 404) {
                    alert("상품 id가 잘못되었습니다.")
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
            url: '/api/v1/product/' + id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            location.href = "/products";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};

main.init();