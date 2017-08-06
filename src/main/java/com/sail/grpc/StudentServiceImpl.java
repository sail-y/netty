package com.sail.grpc;

import com.sail.proto.MyRequest;
import com.sail.proto.MyResponse;
import com.sail.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author yangfan
 * @date 2017/08/01
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息： " + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }
}
