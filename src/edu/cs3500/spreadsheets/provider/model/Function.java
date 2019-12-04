package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

/**
 * Represents a function that can be contained in a cell.
 */
class Function implements Formula {
  //Represents a list of Arguments
  //Private used to be static, idk if that changes anything
  private List<Formula> args;
  private IFunc functionToApply;


  interface IFunc<T, R> {
    Value apply(List<Formula> args);

  }

  Function(IFunc functionToApply, List<Formula> arguments) {
    this.args = arguments; //It should be a list of references
    this.functionToApply = functionToApply;
  }

  public IFunc getFunctionToApply() {
    return this.functionToApply;
  }


  @Override
  public Value getEvaluatedValue() {
    return this.functionToApply.apply(args);
  }

  @Override
  public Formula getRawContents() {
    return this;
  }

  @Override
  public void updateRawContents(Formula form) {
    if (form.isFunction()) {
      this.args = ((Function) form).args;
      this.functionToApply = ((Function) form).functionToApply;
    }
    //Todo: What if it isn't a Function
  }

  @Override
  public void deleteContents() {
    this.args = null;
    this.functionToApply = null;
  }

  @Override
  public boolean isValue() {
    return false;
  }

  @Override
  public boolean isReference() {
    return false;
  }

  @Override
  public boolean isFunction() {
    return true;
  }

}
