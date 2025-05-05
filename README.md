Chatbot Chăm Sóc Khách Hàng Quán Cà Phê
Chào mừng đến với dự án Chatbot chăm sóc khách hàng quán cà phê! Repository này chứa mã nguồn, tài nguyên và hướng dẫn để xây dựng một chatbot sử dụng AI, nhằm nâng cao trải nghiệm khách hàng trong ứng dụng quán cà phê. Sử dụng sức mạnh của các mô hình ngôn ngữ lớn (LLMs), xử lý ngôn ngữ tự nhiên (NLP), và hạ tầng RunPod, chatbot này có thể hỗ trợ gọi món, trả lời các câu hỏi về thực đơn, và gợi ý sản phẩm cá nhân hóa — tất cả tích hợp trong một ứng dụng mobile React Native.

Tổng Quan Dự Án
Mục tiêu là xây dựng một chatbot theo kiến trúc tác tử (agent-based) thông minh có thể:

Xử lý tương tác trực tiếp với khách hàng trong thời gian thực, bao gồm cả gọi món.

Trả lời câu hỏi về thực đơn, bao gồm nguyên liệu và dị ứng thông qua hệ thống RAG (Retrieval-Augmented Generation).

Gợi ý sản phẩm cá nhân hóa bằng công cụ đề xuất dựa trên phân tích giỏ hàng (market basket analysis).

Hướng dẫn khách hàng qua quá trình gọi món một cách chính xác và có cấu trúc.

Ngăn chặn những truy vấn không phù hợp nhờ vào “Guard Agent”.


Kiến Trúc Agent Của Chatbot:

Chatbot được xây dựng theo kiến trúc modular, nơi mỗi tác tử đảm nhận một nhiệm vụ cụ thể, giúp tối ưu hóa tương tác giữa người dùng và dịch vụ quán cà phê. Điều này giúp hệ thống dễ mở rộng, linh hoạt và dễ bảo trì.

Các Tác Tử Chính
Guard Agent
Tác tử bảo vệ hệ thống, chặn các câu hỏi không phù hợp, gây hại hoặc không liên quan.

Order Taking Agent
Hướng dẫn người dùng từng bước đặt món, đảm bảo thông tin chính xác và đầy đủ nhờ kỹ thuật "chain-of-thought prompting".

Details Agent (Hệ thống RAG)
Trả lời câu hỏi chi tiết về thực đơn, nguyên liệu, dị ứng… bằng cách truy xuất thông tin từ cơ sở dữ liệu vector và kết hợp với mô hình ngôn ngữ.

Recommendation Agent
Đưa ra gợi ý sản phẩm cá nhân hóa dựa trên đơn hàng hiện tại hoặc sở thích người dùng.

Classification Agent
Phân loại truy vấn của người dùng để quyết định nên chuyển cho tác tử nào xử lý.

Cách Các Agent Hoạt Động Cùng Nhau
Truy vấn của khách hàng sẽ được Guard Agent kiểm tra.

Nếu hợp lệ, Classification Agent sẽ xác định mục đích truy vấn.

Sau đó truy vấn được chuyển tới:

Order Taking Agent để xử lý đơn hàng.

Details Agent để lấy thông tin thực đơn.

Recommendation Agent để gợi ý thêm sản phẩm.


Ứng Dụng Mobile Quán Cà Phê (React Native)

Ứng dụng React Native là giao diện người dùng chính để tương tác với chatbot và khám phá thực đơn. Giao diện thân thiện, trực quan, tích hợp chatbot giúp khách hàng:

Đặt món

Nhận gợi ý sản phẩm

Xem thông tin chi tiết về món ăn

Các Chức Năng Chính:
Landing Page: Trang chào mừng.

Home Page: Hiển thị thực đơn nổi bật.

Item Details Page: Chi tiết món (nguyên liệu, dị ứng...).

Cart Page: Xem và chỉnh sửa đơn hàng.

Chatbot Interface: Tương tác trực tiếp với chatbot AI.


Cấu Trúc Thư Mục
├── coffee_shop_customer_service_chatbot
│   ├── coffee_shop_app_folder       # Mã nguồn ứng dụng React Native
│   ├── python_code
│       ├── API/                     # Mã API hệ thống agent chatbot
│       ├── dataset/                 # Dữ liệu huấn luyện công cụ gợi ý
│       ├── products/                # Dữ liệu sản phẩm (tên, giá, mô tả, ảnh)
│       ├── build_vector_database.ipynb             # Tạo vector DB cho RAG
│       ├── firebase_uploader.ipynb                 # Đưa dữ liệu lên Firebase
│       ├── recommendation_engine_training.ipynb    # Huấn luyện engine gợi ý


Mỗi thư mục sẽ có hướng dẫn khởi động riêng để triển khai từng phần (frontend, backend) một cách độc lập.


Liên Kết Tham Khảo

RunPod – Hạ tầng triển khai mô hình máy học.

Kaggle Dataset – Dữ liệu huấn luyện công cụ gợi ý.

Thiết kế Figma – Giao diện mẫu ứng dụng.

Hugging Face – LLaMA – Mô hình NLP dùng trong chatbot.

Pinecone – Hướng dẫn sử dụng Pinecone cho vector DB.

Firebase – Tài liệu Firebase để quản lý dữ liệu ứng dụng.