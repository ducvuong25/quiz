# Yêu cầu hệ thống
- Apache netbean 12
- Mysql version: 8.0.22
- JDK 8 hoặc cao hơn
# Một số chý ý
- Để deploy project lên được tomcat server thì cần phải đổi tên file war thành ROOT.war, chi tiêt xem thêm tại [đây](https://www.baeldung.com/tomcat-root-application).
- ``Application Context`` được thiệt lập = "".
- Không dùng thẻ ``<c:url>`` vì rườm rà không cần thiết.
- Sử dụng phiên bản ``MySQL8Dialect`` để generate ra câu lệnh SQL phù hợp
- Method khởi tạo dữ liệu ban đầu là ``webservice.initdata()``  được mark với ``@PostConstruct`` ==> tạo ra 4 question và 1 Test.
# Cách làm màn hình cuối

- Thêm một thuộc tính ``selected`` kiểu ``boolean`` với annotation  ``@Transient`` để nó không lưu vào database
- Trước khi người dùng vào trang  "thêm câu hỏi cho từng bài test": Đi so khớp giữa 2 list:
  1. list tất cả câu hỏi trong hệ thống
  2. list các câu hỏi đã nằm trong test này trước đó
- Dùng các xử lý logic if...else để gán thuộc tính ``selected`` là ``true`` cho những question được check trước đó.
- sau khi người dùng ấn save ở màn hình "thêm câu hỏi cho từng bài test":
  1. Ta có một list các question vừa được chọn, gọi method `test.setQuestions(List question mới)` ==>(drop cái list cũ đi)
  2. Tiến hành lưu bài test đó lại và database.
