public StudentResponseDto findAllStudentById(Integer id) {
    return repository.findById(id)
            .map(studentMapper::toStudentResponseDto)
            .orElse(null);
}

@Test
public void shouldReturnStudentById() {
    // Given
    int studentId = 1;
    Student student = new Student("CK" , "Doe" , yck11214@gmail.com , 29);


    // Mcok the calss
    when(repository.findById(studentId))
        .thenReturn(Optional.of(student));


    // Mock the calls
    when(studentMapper.toStudent(dto)).thenReturn(student);
    when(repository.save(student)).thenReturn(student);
    when(studentMapper.toStudentResponseDto(savedStudent))
        .thenReturn(new StudentResponseDto(
        "CK" 
        , "Doe" , 
        "yck11214@gmail.com",
        ));
    
    // When
    StudentResponseDto dto = studentService.findAllStudentById(StudentId);

    //Then
    assertEquals(dto.firstname() , student.getFirstname());
    assertEquals(dto.lastname() , student.getLasttname());
    assertEquals(dto.email() , student.getEmail());

    verify(repository , times(1)).findById(studentId);
        
}