/* Add your custom JavaScript code */


    var Parameter = {
    url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
    method: "GET",
    responseType: "application/json",
};

    var promise = axios(Parameter);
    promise.then(function (result) {
    var data = result.data;

    var citySelect = document.getElementById("city");
    var districtSelect = document.getElementById("district");
    var wardSelect = document.getElementById("ward");

    // Lặp qua danh sách thành phố và điền vào phần tử <select> city
    for (var i = 0; i < data.length; i++) {
    var city = data[i];
    if (city.Name === "Thành phố Hồ Chí Minh") {
    var option = document.createElement("option");
    option.value = city.Id;
    option.innerText = city.Name;
    citySelect.appendChild(option);

    // Sắp xếp danh sách quận/huyện theo thứ tự
    var sortedDistricts = city.Districts.sort(function (a, b) {
    return a.Name.localeCompare(b.Name);
});

    // Lặp qua danh sách quận/huyện của thành phố Hồ Chí Minh và điền vào phần tử <select> district
    for (var j = 0; j < sortedDistricts.length; j++) {
    var district = sortedDistricts[j];
    var districtOption = document.createElement("option");
    districtOption.value = district.Id;
    districtOption.innerText = district.Name;
    districtSelect.appendChild(districtOption);
}
}
}

    // Xử lý sự kiện onchange của districtSelect
    districtSelect.onchange = function () {
    var selectedDistrictId = this.value;

    // Xóa các option hiện tại của wardSelect
    wardSelect.innerHTML = '<option value="" selected>Chọn phường xã</option>';

    if (selectedDistrictId !== "") {
    // Tìm quận/huyện dựa trên mã quận/huyện đã chọn
    var selectedCity = data.find(function (city) {
    return city.Name === "Thành phố Hồ Chí Minh";
});

    var selectedDistrict = selectedCity.Districts.find(function (district) {
    return district.Id === selectedDistrictId;
});

    // Lặp qua danh sách phường/xã của quận/huyện và điền vào phần tử <select> ward

    // Sắp xếp danh sách phường/xã theo thứ tự
    var sortedWards = selectedDistrict.Wards.sort(function (a, b) {
    return a.Name.localeCompare(b.Name);
});

    for (var k = 0; k < sortedWards.length; k++) {
    var ward = sortedWards[k];
    var wardOption = document.createElement("option");
    wardOption.value = ward.Id;
    wardOption.innerText = ward.Name;
    wardSelect.appendChild(wardOption);
}
}
};
});
    document.getElementById("address").onchange = getAddress;
    document.getElementById("ward").onchange = getAddress;
    document.getElementById("district").onchange = getAddress;
    document.getElementById("city").onchange = getAddress;

    function getAddress(){
        var address = document.getElementById("address").value;
        var wardSelect = document.getElementById("ward");
        var districtSelect = document.getElementById("district");
        var citySelect = document.getElementById("city");

        var ward = wardSelect.selectedIndex !== -1 ? wardSelect.options[wardSelect.selectedIndex].text : '';
        var district = districtSelect.selectedIndex !== -1 ? districtSelect.options[districtSelect.selectedIndex].text : '';
        var city = citySelect.selectedIndex !== -1 ? citySelect.options[citySelect.selectedIndex].text : '';

        var delivery_address = address + ', ' + ward + ', ' + district + ', ' + city;
        var current_address = document.getElementById("current_address");
        current_address.value = delivery_address;

    }

function formatNumber(num) {
    try {
        if (num == null) return "";

        if (!$.isNumeric(num)) return "0";

        var p = num.toFixed(2).split(".");
        return p[0].split("").reverse().reduce(function (acc, num, i, orig) {
            return num + (i && !(i % 3) ? "," : "") + acc;
        }, "") + (p[1] != null && p[1] != "00" ? "." + p[1] : "");
    }
    catch (ex) {
        return "0";
    }
}

