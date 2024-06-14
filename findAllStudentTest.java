public List<StudentResponseDto> findAllStudent() {
    return repository.findAll()
            .stream()
            .map(studentMapper::toStudentResponseDto)
            .collect(Collectors.toList());
}

 @Test
public void findAllStudentTest() {

    //Given
    List<Student> student = new ArrayList<>();
    student.add(Student("CK" , "Doe" , yck11214@gmail.com , 29));

    //Inject
    @Inject
    private StudentService studentService;

    //@Mock
    private studentMapper toStudentResponseDto;

    //Mock
    when(repository.findAll()).thenReturn(students);
    when(studentMapper.toStudentResponseDto(any(Student.class)))
    .thenReturn(new StudentResponseDto("John" , "Doe" , yck11214@gmail.com))

    //When
    List<StudentResponseDto> responseDto = studentService.findAllStudent();

    //Then
    assertEquals(students.size() , responseDto.size());
    verify(repository , times(1)).findAll();

}

