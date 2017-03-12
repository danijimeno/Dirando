$(document).ready(function () {
    $("#xco").on("click", function (event) {
        $("#searchCat").val("xco");
        $("#searchByCat").submit();
    });
    $("#downhill").on("click", function (event) {
        $("#searchCat").val("downhill");
        $("#searchByCat").submit();
    });
    $("#road").on("click", function (event) {
        $("#searchCat").val("road");
        $("#searchByCat").submit();
    });
    $("#cyclocross").on("click", function (event) {
        $("#searchCat").val("cyclocross");
        $("#searchByCat").submit();
    });
});