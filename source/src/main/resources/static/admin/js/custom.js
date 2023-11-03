function setTodayDate() {
    var currentDate = new Date(); // Lấy đối tượng Date hiện tại
    var day = currentDate.getDate(); // Lấy ngày
    var month = currentDate.getMonth() + 1; // Lấy tháng (lưu ý: tháng được đánh số từ 0 đến 11, nên cần cộng thêm 1)
    var year = currentDate.getFullYear(); // Lấy năm

    // Chuẩn hóa định dạng ngày và tháng thành "dd" và "mm"
    if (day < 10) {
        day = '0' + day;
    }
    if (month < 10) {
        month = '0' + month;
    }

    var formattedDate = year + '-' + month + '-' + day; // Tạo định dạng "yyyy-MM-dd"

    var dateInput = document.getElementById('from');// Thay 'your-input-id' bằng ID của phần tử input của bạn
    var dateInput2 = document.getElementById('to');
    dateInput.value = formattedDate; // Gán giá trị mặc định cho phần tử input
    dateInput2.value = formattedDate;
}

// const inputElement = document.getElementById("myNumber");
// inputElement.addEventListener("input", function (){
//     console.log("Change value: " + inputElement.value)
// })
//
// inputElement.addEventListener("stepUp", function (){
//     var currentValue = parseInt(inputElement.value);
//     var increment = 1000;
//     var newValue = currentValue + increment;
//     inputElement.value = newValue;
//     console.log("Clicked step up button")
// })
//
// inputElement.addEventListener("keydown", function (){
//     console.log("Clicked step down button")
// })