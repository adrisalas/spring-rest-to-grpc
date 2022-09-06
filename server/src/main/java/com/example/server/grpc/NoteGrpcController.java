package com.example.server.grpc;

import com.example.proto.EmptyDto;
import com.example.proto.NoteDto;
import com.example.proto.NoteIdDto;
import com.example.proto.NotesDto;
import com.example.server.business.Note;
import com.example.server.business.NoteService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import static com.example.proto.NoteServiceGrpc.NoteServiceImplBase;

@Controller
@RequiredArgsConstructor
public class NoteGrpcController extends NoteServiceImplBase {
    private final NoteService noteService;

    @Override
    public void findAll(EmptyDto request, StreamObserver<NotesDto> responseObserver) {
        responseObserver.onNext(NotesDto.newBuilder()
                                        .addAllNotes(noteService.findAll()
                                                                .stream()
                                                                .map(this::toGrpc)
                                                                .toList())
                                        .build());
        responseObserver.onCompleted();
    }

    @Override
    public void findById(NoteIdDto request, StreamObserver<NoteDto> responseObserver) {
        responseObserver.onNext(toGrpc(noteService.findById(request.getId())));
        responseObserver.onCompleted();
    }

    @Override
    public void save(NoteDto request, StreamObserver<EmptyDto> responseObserver) {
        noteService.save(toModel(request));
        responseObserver.onNext(EmptyDto.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(NoteIdDto request, StreamObserver<EmptyDto> responseObserver) {
        noteService.delete(request.getId());
        responseObserver.onNext(EmptyDto.getDefaultInstance());
        responseObserver.onCompleted();
    }

    private NoteDto toGrpc(Note note) {
        return NoteDto.newBuilder()
                      .setId(note.getId())
                      .setMessage(note.getMessage())
                      .build();
    }

    private Note toModel(NoteDto noteDto) {
        return Note.builder()
                   .id(noteDto.getId())
                   .message(noteDto.getMessage())
                   .build();
    }
}
