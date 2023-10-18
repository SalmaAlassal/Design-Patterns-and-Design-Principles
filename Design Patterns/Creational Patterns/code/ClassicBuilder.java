package creational.code;

// Product
class Report {
    private String header;
    private String body;
    private String footer;

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getFooter() {
        return footer;
    }
    
    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

}

// Builder interface
interface IReportBuilder {
    void BuildHeader();
    void BuildBody();
    void BuildFooter();
    Report GetReport();
}

// Concrete Builders
class SimpleReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void BuildHeader() {
        report.setHeader("Simple Header");
    }

    @Override
    public void BuildBody() {
        report.setBody("Simple Body");
    }

    @Override
    public void BuildFooter() {
        report.setFooter("Simple Footer");
    }

    @Override
    public Report GetReport() {
        return report;
    }
}

class ComplexReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void BuildHeader() {
        report.setHeader("Complex Header");
    }

    @Override
    public void BuildBody() {
        report.setBody("Complex Body");
    }

    @Override
    public void BuildFooter() {
        report.setFooter("Complex Footer");
    }

    @Override
    public Report GetReport() {
        return report;
    }
}

// Director
class ReportDirector {
    private IReportBuilder reportBuilder;

    public ReportDirector(IReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }

    public Report GetReport() {
        return reportBuilder.GetReport();
    }

    public void BuildReport() {
        reportBuilder.BuildHeader();
        reportBuilder.BuildBody();
        reportBuilder.BuildFooter();
    }
}

// Client
class ClassicBuilder {
    public static void main(String[] args) {
        ReportDirector reportDirector = new ReportDirector(new SimpleReportBuilder());
        reportDirector.BuildReport();
        Report report = reportDirector.GetReport();
        // Print report
        System.out.println(report.getHeader());
        System.out.println(report.getBody());
        System.out.println(report.getFooter());

        reportDirector = new ReportDirector(new ComplexReportBuilder());
        reportDirector.BuildReport();
        report = reportDirector.GetReport();
        // Print report
        System.out.println(report.getHeader());
        System.out.println(report.getBody());
        System.out.println(report.getFooter());

    }
}