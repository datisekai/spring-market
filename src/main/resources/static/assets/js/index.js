const handleAddQuery = (key, value) => {
    // Lấy tham số truy vấn hiện tại từ URL
    const searchParams = new URLSearchParams(window.location.search);

// Thêm tham số truy vấn mới vào danh sách các tham số
    searchParams.set(key, value);

// Tạo URL mới với các tham số truy vấn mới được thêm vào
    const newUrl = window.location.pathname + '?' + searchParams.toString();

// Chuyển hướng đến URL mới
    window.location.href = newUrl;
}

const categories = document.querySelectorAll('.category');

categories.forEach(item => {
    item.onclick = () => {
        const id = item.getAttribute('data-id');
        handleAddQuery("category",id);
    }
})

