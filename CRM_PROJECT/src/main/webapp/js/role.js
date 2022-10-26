//$ => jquery
$(document).ready(function () {
    //selector: chỉ đọc dc id hoặc class
    // this: đại diện cho thẻ đang xảy ra sự kiện
    //parent(): Đi 1 cấp ra thằng cha của thẻ đang dc selector gọi
    // closest(): Đi ra n cấp chỉ định (Đi ra tổ tiên)
    $(".btn-delete").click(function () {
        var id = $(this).attr("roleId")
        var This = $(this);
        $.ajax({
            method: "get",
            url: "http://localhost:8081/crm/api/role?id="+id,
            //data: {
        }).done(function (data) {
            console.log(data);
            if(data.success){
                //xoa thanh cong
                This.closest("tr").remove()
            } else{
                //xoa that bai
                alert("xoa that bai")
            }
        })
    })
})
