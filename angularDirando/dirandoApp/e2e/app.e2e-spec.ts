import { DirandoAppPage } from './app.po';

describe('dirando-app App', function() {
  let page: DirandoAppPage;

  beforeEach(() => {
    page = new DirandoAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
