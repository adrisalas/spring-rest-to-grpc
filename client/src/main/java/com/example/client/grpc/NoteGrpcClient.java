package com.example.client.grpc;

import com.example.client.business.Note;
import com.example.client.business.NoteRepository;
import com.example.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
class NoteGrpcClient implements NoteRepository {
    private final EmptyDto EMPTY = EmptyDto.newBuilder().build();
    private final NoteServiceGrpc.NoteServiceBlockingStub stub;

    public NoteGrpcClient(@Value("${grpc-to-database.address}") String address,
                          @Value("${grpc-to-database.port}") int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port)
                                                      .usePlaintext()
                                                      .maxInboundMessageSize(Integer.MAX_VALUE)
                                                      .build();
        stub = NoteServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public Note findById(int id) {
        return toBusiness(stub.findById(toProto(id)));
    }

    @Override
    public Collection<Note> findAll() {
        List<Note> notes = new ArrayList<>();
        stub.findAll(EmptyDto.newBuilder().build())
            .forEachRemaining(note -> notes.add(toBusiness(note)));
        return notes;
    }

    public Collection<Note> findAllBadIdea() {
        return toBusiness(stub.findAllBadIdea(EMPTY));
    }

    @Override
    public void save(Note note) {
        stub.save(toProto(note));
    }

    @Override
    public void delete(int id) {
        stub.delete(toProto(id));
    }

    private NoteIdDto toProto(int id) {
        return NoteIdDto.newBuilder()
                        .setId(id)
                        .build();
    }

    private NoteDto toProto(Note note) {
        return NoteDto.newBuilder()
                      .setId(note.getId())
                      .setMessage(note.getMessage())
                      .build();
    }

    private Note toBusiness(NoteDto noteDto) {
        return Note.builder()
                   .id(noteDto.getId())
                   .message(noteDto.getMessage())
                   .build();
    }

    private List<Note> toBusiness(NotesDto notesDto) {
        return notesDto.getNotesList()
                       .stream()
                       .map(this::toBusiness)
                       .toList();
    }
}
