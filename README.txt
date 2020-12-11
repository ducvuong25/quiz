Project được viết và chạy tốt trên:

- Apache netbean 12
- Mysql version: 8.0.22 (Thầy nhớ đổi userName vs pass datasources phù hợp với thầy)


Một số chú ý trong trong cách làm của em và:
- EM Thiết lập context path ="" ==> sau khi deploy lên chỉ cần truy cập localhost:8080 là vào được index
- Tất cả mọi link đến resourses (css, image, url, jsp) em đều dùng root relative path (/images/vuong.jpg)
- Vì 2 thiết lập ở trên nên em bỏ hẳn thẻ <c:url> của jstl ==> vì không sợ lỗi 403, 404
- Sau khi đã thử các kiểu DIALECT thì chỉ thấy  DIALECT Có tên MySQL8Dialect là generate ra câu lệnh mysql đúng không bị lỗi gì nên em dùng nó
- Mặc định method khởi tạo dữ liệu ban đầu  là initdata() trong class  webservice được mark với @PostConstruct ==> tạo ra 4 question và 1 Test
- Vì cấu hình trên nên khi đã deploy lên rồi mà sửa code rồi save thì nó lại redeploy => chạy initdata() thêm lần nữa ==> trùng lặp dữ liệu
- Về Cách làm màn hình cuối (thêm question vào trong test)
	+Thêm một thuộc tính selected kiểu boolean với annotation  @Transient để nó không lưu vào database ==> lưu cũng không có ý nghĩa
	+ Trước khi người dùng vào trang  "thêm câu hỏi cho từng bài test": em đi so khớp giữa 2 list:
		+ list tất cả câu hỏi trong hệ thống
		+ list các câu hỏi đã nằm trong test này trước đó
		==> dùng if ellse để set thuộc tính selected là true ==> để làm điều kiện check hay uncheck trong JSTL 
	+ sau khi người dùng ấn save ở màn hình "thêm câu hỏi cho từng bài test":
		+ em có một list các question vừa được chọn==> em tiến hành gọi method test.setQuestions(List question mới) ==>(drop cái list cũ đi)
		+ Tiến hành lưu bài test đó lại và database.
	==> cứ tiếp tục vòng tuần hoàn như vậy
	
	




