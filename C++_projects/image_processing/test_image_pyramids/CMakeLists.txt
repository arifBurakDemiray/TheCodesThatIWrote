
cmake_minimum_required(VERSION 3.16)

project(ceng391_02T CXX)

set(CMAKE_BUILD_TYPE Release)

include(FindPNG)
include_directories(${PNG_INCLUDE_DIR})
link_directories(${PNG_LIBRARY_DIR})

add_executable(image-pyr-test image_pyr.cc image.cc image_pyr_test.cc)
target_link_libraries(image-pyr-test ${PNG_LIBRARIES})

 