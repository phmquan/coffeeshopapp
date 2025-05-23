Thư Mục API
Thư mục này chứa mã nguồn dùng để triển khai hệ thống chatbot dựa trên kiến trúc tác tử (agent-based). Đây được xem là phần backend của ứng dụng. Dưới đây là tổng quan về cấu trúc thư mục và các thành phần của nó.

Cấu Trúc Thư Mục
Thư Mục Agents
Thư mục agents tuân theo kiến trúc dựa trên tác tử, trong đó mỗi tác tử đảm nhận một nhiệm vụ cụ thể trong hệ thống chatbot:

guard_agent.py: Có nhiệm vụ chặn các câu hỏi không liên quan hoặc độc hại.

classification_agent.py: Phân loại đầu vào của người dùng và xác định tác tử nào sẽ phản hồi.

details_agent.py: Xử lý các câu hỏi liên quan đến thông tin quán cà phê và thực đơn.

order_taking_agent.py: Quản lý quá trình tiếp nhận đơn hàng, đảm bảo dữ liệu đơn hàng rõ ràng và chính xác.

recommendation_agent.py: Tương tác với hệ thống gợi ý để cung cấp đề xuất sản phẩm cá nhân hóa.

utils.py: Chứa các hàm tiện ích như:

Lấy phản hồi từ mô hình ngôn ngữ lớn (LLM).

Tạo embedding.

Xác thực đầu ra JSON cho dữ liệu có cấu trúc.

Thư Mục Recommendation Objects
recommendation_objects: Chứa các mô hình gợi ý đã được huấn luyện, được sử dụng bởi recommendation_agent.py để đề xuất sản phẩm.

Các Tập Tin Khác
agent_controller.py: Điều phối tương tác giữa các tác tử, quản lý luồng thông tin và phản hồi.

main.py: Gọi agent_controller và tích hợp với tính năng triển khai của RunPod.

Dockerfile: Dùng để xây dựng mã nguồn thành image Docker phục vụ triển khai.

Triển Khai Trên RunPod
Để triển khai API chatbot trên RunPod:

Đẩy Docker Image:
Đẩy tập tin Dockerfile lên tài khoản DockerHub, hoặc có thể sử dụng image dựng sẵn:

bash
Copy
Edit
abdullah57/chatbot
Tạo Endpoint Trên RunPod:

Truy cập phần serverless của RunPod và tạo một endpoint mới.

Điền đầy đủ thông tin cần thiết, bao gồm DockerHub image.

Thêm các biến môi trường cần thiết từ file .env.

Chạy Code Trên Máy Tính Cục Bộ
Thiết Lập Biến Môi Trường:
Đảm bảo các biến môi trường cần thiết đã được thiết lập trong file .env.

Cài Đặt Thư Viện:
Cài đặt các thư viện cần thiết bằng lệnh:

bash
Copy
Edit
pip install -r requirements.txt
Chạy Code:
Có thể chạy chatbot local bằng lệnh:

bash
Copy
Edit
python development_code.py
