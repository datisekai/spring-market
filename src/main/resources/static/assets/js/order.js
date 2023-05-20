const handleViewOrderDetail=(id) => {
    var dataid = id.getAttribute("data-id");
    window.location.href = "/orderdetail?id="+dataid; 
}

