project(ceng391_homework02 CXX)

cmake_minimum_required(VERSION 3.16)

set(CMAKE_BUILD_TYPE Release)

include(FindPNG)
include_directories(${PNG_INCLUDE_DIR})
link_directories(${PNG_LIBRARY_DIR})

add_executable(test-image image.cc image_test.cc)
target_link_libraries(test-image ${PNG_LIBRARIES})
