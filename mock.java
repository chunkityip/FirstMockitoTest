public void shouldSuccessFullySaveAStudnet() {

    //Which service class we want to test so we can inject the mocj into the class
    @InjnectMocks
    private StudentService studentService;

    //Declare the dependencies
    //Mock the constractor 
    @Mock
    StudentRepositry repository;

    @Mock
    StudentMapper studentMapper; 

    //tell mockito to open the mock for this current class
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void saveStudnetShouldPass() {
        //Given
        StudentDto dto = new Student("Chunkit" , "Yip" , yck11214@gmail.com , 25);

        Student student = new Student("Chunkit" , "Yip" , yck11214@gmail.com , 31);

        Student savedStudent = new Student("CK" , "Yip" , yck11214@gmail.com , 35);
        savedStudent.setId(1);


        //Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(student);
        when(studentMapper.toStudentResponseDto(savedStudent))
            .thenReturn(new StudentResponseDto(
            "CK" 
            , "Yip" , 
            "yck11214@gmail.com")
            );

        //When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        //Then
        assertEquals(dto.firstname() , responseDto.firstname());
        assertEquals(dto.lastname() , responseDto.firstname());
        assertEquals(dto.email() , responseDto.firstname());

        //Mock the object
        verify(studentMapper , times(1)).toStudent(dto);
        verify(repository , times(1)).save(student);
        verify(studentMapper , times(1)).toStudentResponseDto(savedStudent);

    }


}


/*
@Service
public class StudentService {

    private final StudentRepositry repository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }
}