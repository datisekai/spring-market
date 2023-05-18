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

const formSearch = document.getElementById('form-search');

formSearch.onsubmit = (e) => {
    handleSearch(e);
}

const handleSearch =  (e) => {
    e.preventDefault();
    const inputValue = document.getElementById("search-input").value;
    if(inputValue.trim().length > 0){
        handleAddQuery("name",inputValue);
    }
}
const valueSearch= ()=> {
    const searchParams = new URLSearchParams(window.location.search);
    const param1Value = searchParams.get('name');
    return param1Value;
}
document.getElementById("search-input").value= valueSearch();
const activeBestSeller= ()=> {
    const searchParams = new URLSearchParams(window.location.search);
    const param1Value = searchParams.get('sort');
    if(param1Value === "best-seller"){
        document.getElementById("btn-bestSeller").style.backgroundColor = "rgb(32,135,67)";
    }
}
const activeCategory= ()=> {
    const searchParams = new URLSearchParams(window.location.search);
    const param1Value = searchParams.get('category');
    categories.forEach(item => {
        const id = item.getAttribute('data-id');
        if(param1Value === id){
            document.querySelector(`[data-id="${id}"]`).style.textDecoration = 'underline';
        }
    })
}
activeCategory();
activeBestSeller();